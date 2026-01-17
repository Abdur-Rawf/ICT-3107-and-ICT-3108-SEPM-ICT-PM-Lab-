package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/hello")  // Annotation-based mapping
public class HelloWorldServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        
        // Get PrintWriter
        PrintWriter out = response.getWriter();
        
        // Write HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World Servlet</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }");
        out.println("h1 { color: #2E86C1; }");
        out.println(".container { background-color: #F2F3F4; padding: 20px; border-radius: 10px; display: inline-block; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>🌍 Hello World from Servlet!</h1>");
        out.println("<p>Servlet is running successfully ✅</p>");
        out.println("<p>Current Time: " + new java.util.Date() + "</p>");
        out.println("<p>Servlet Info: " + getServletInfo() + "</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        
        // Close writer
        out.close();
    }
    
    @Override
    public String getServletInfo() {
        return "HelloWorldServlet v1.0 - Created for Servlet Assignment";
    }
}
