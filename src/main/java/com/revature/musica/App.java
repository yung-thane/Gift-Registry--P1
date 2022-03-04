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

public class App {
    public static void main(String[] args){

        //Makes new Tomcat server.
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
                IOUtils.copy(file,resp.getOutputStream());
            }
            //Handles anything that comes after the slash, the * signifies anything.
        }).addMapping("/*");

        //Attempts to start the server, surrounded by try/catch to handle any exceptions.
        try {
            server.start();
        } catch (LifecycleException e) {
            // e.getMessage() gives us the error message instead of the full stack trace.
            System.err.println("Failed to start the server: " + e.getMessage());
        }


    }
}
