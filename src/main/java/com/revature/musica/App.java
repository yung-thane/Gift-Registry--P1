package com.revature.musica;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


class Item{
    private int itemId;
    private String name;

    public Item(int itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }
    public Item() {
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", name=" + name + "]";
    }
   
}


public class App {
    public static void main(String[] args) throws SQLException{
        //Connect to DB, telling connection object to connect to H2 DB in memory, but initialize by running script found from classpath: schema.sql
        //String url = "jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'";
        String url = "jdbc:postgresql://musica-db:5432/postgres";
        String username = "postgres";
        String password = "postgres";
        Connection connection = DriverManager.getConnection(url, username, password);
     
        //Makes new Http servlet named ItemServlet
        HttpServlet itemServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                        List<Item> items = new ArrayList<>();
                        try {
                            //Creates result set.
                            ResultSet rs = connection.prepareStatement("Select * from Item").executeQuery();
                            while(rs.next()){
                                items.add(new Item(rs.getInt("ItemId"), rs.getString("Name")));
                            }
                } catch (SQLException e) {
                    System.err.println("Failed to retrieve from db: " + e.getSQLState());;
                }
                    //Get a JSON Mapper, we then use the mapper.writeValueAsString to convert our items object into a JSON string and then print it to the response.
                    ObjectMapper mapper = new ObjectMapper();
                    String results = mapper.writeValueAsString(items);
                    resp.setContentType("application/json");
                    resp.getWriter().println(results);
            }
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                //Makes a new ObjectMapper named mapper and uses it with readValue(Input Stream, Type) to make a String named newArtist from the input sent by the Post.
                ObjectMapper mapper = new ObjectMapper();
                Item newItem = mapper.readValue(req.getInputStream(), Item.class);
                System.out.println(newItem);

                try {
                    PreparedStatement stmt = connection.prepareStatement("insert into Item values(?, ?)");
                    stmt.setInt(1, newItem.getItemId());
                    stmt.setString(2, newItem.getName());
                    //Can't forget to execute statement, or it will just not happen.
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Failed to insert: " + e.getMessage());
                }
            }
        };

        //Makes and runs new Tomcat server.
        Tomcat server = new Tomcat();
        //Starts up localhost:8080 http connector
        server.getConnector();
        server.addContext("", null);
        //Essentially gives us the portion of a web address after the main website name and slash. google.com/ThisPartHere
        //When empty, we can set to as our default, it's the first thing seen when someone just types the google.com portion.
        //The mapping will add what comes after the /, or google.com/ThisPartHere
        server.addServlet("", "defaultServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                //Takes in request of Path info, then replaces the first slash with blank and saves it to filename String.
                String filename = req.getPathInfo(); // We removed the following and made a directory instead.replaceFirst("/", "");
                //Making a String named resourceDir with the name of the folder saved.
                String resourceDir = "static";
                //Makes an input stream using getClass.getClassLoader.getResourcesAsStream and passing the resourceDir and filename we
                //built in the prior step as the parameter.
                InputStream file = getClass().getClassLoader().getResourceAsStream(resourceDir + filename);
                //Uses getServletContext and getMimeType to deduce the file type of the file which was passed through as
                //a parameter and saves it to a String named mimeType.
                String mimeType = getServletContext().getMimeType(filename);
                //Copies our input stream named file to a response output stream which is then displayed to us.
                resp.setContentType(mimeType);
                IOUtils.copy(file,resp.getOutputStream());
            }
            //Handles anything that comes after the slash, the * signifies anything.
        }).addMapping("/*");
        
        server.addServlet("", "itemServlet", itemServlet).addMapping("/items");

        //Attempts to start the server, surrounded by try/catch to handle any exceptions.
        try {
            server.start();
        } catch (LifecycleException e) {
            // e.getMessage() gives us the error message instead of the full stack trace.
            System.err.println("Failed to start the server: " + e.getMessage());
        }


    }
}
