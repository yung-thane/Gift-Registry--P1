package com.revature.musica;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DefaultController extends HttpServlet{
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
    
}
