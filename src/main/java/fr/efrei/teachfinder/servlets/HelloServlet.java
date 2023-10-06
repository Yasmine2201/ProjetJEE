package fr.efrei.teachfinder.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

import static fr.efrei.teachfinder.utils.Constants.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    public void init(){
    }

    public void destroy() {
    }
}