package fr.efrei.teachfinder.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static fr.efrei.teachfinder.utils.Constants.INDEX_PAGE;
import static fr.efrei.teachfinder.utils.Constants.INSCRIPTION_PAGE;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(INSCRIPTION_PAGE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public void init() {
    }

    public void destroy() {
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        switch (request.getParameter("NavBar")) {
            case "Connexion":
                Connexion(request, response);
                break;
            case "Inscription":
                Inscription(request, response);
                break;
            default:
                request.getRequestDispatcher("index.jsp");


        }
    }

    public void Connexion(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("index.jsp");
    }

    public void Inscription(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("index.jsp");
    }

}