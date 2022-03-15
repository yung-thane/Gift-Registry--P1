package com.revature.musica;

import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException{
      AppConfig beanFactoryAppConfig = new AppConfig();
        HttpServlet itemServlet = beanFactoryAppConfig.getItemController();

        Tomcat server = new Tomcat();
        //Starts up localhost:8080 http connector
        server.getConnector();
        server.addContext("", null);
        //Essentially gives us the portion of a web address after the main website name and slash. google.com/ThisPartHere
        //When empty, we can set to as our default, it's the first thing seen when someone just types the google.com portion.
        //The mapping will add what comes after the /, or google.com/ThisPartHere
        server.addServlet("", "defaultServlet", new DefaultController()).addMapping("/*");
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
