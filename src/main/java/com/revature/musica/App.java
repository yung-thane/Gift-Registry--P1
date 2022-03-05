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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) throws SQLException{
        //Connect to DB, telling connection object to connect to H2 DB in memory, but initialize by running script found from classpath: schema.sql
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        //Creates result set.
        ResultSet rs = connection.prepareStatement("Select * from artist").executeQuery();
        List<String> artists = new ArrayList<>();
        while(rs.next()){
            artists.add(rs.getString("Name"));
        }



        //Makes new Http servlet named ArtistServlet
        HttpServlet artistServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                    //Get a JSON Mapper, we then use the mapper.writeValueAsString to convert our artists object into a JSON string and then print it to the response.
                    ObjectMapper mapper = new ObjectMapper();
                    String results = mapper.writeValueAsString(artists);
                    resp.setContentType("application/json");
                    resp.getWriter().println(results);
            }
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                //Makes a new ObjectMapper named mapper and uses it with readValue(Input Stream, Type) to make a String named newArtist from the input sent by the Post.
                ObjectMapper mapper = new ObjectMapper();
                String newArtist = mapper.readValue(req.getInputStream(), String.class);
                try {
                    PreparedStatement connection.prepareStatement("insert into 'artist' values(?, ?)")
                    .setInt(1, 3);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
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
        
        server.addServlet("", "artistServlet", artistServlet).addMapping("/artists");

        //Attempts to start the server, surrounded by try/catch to handle any exceptions.
        try {
            server.start();
        } catch (LifecycleException e) {
            // e.getMessage() gives us the error message instead of the full stack trace.
            System.err.println("Failed to start the server: " + e.getMessage());
        }


    }
}
