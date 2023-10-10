package fr.efrei.teachfinder.servlets;

import fr.efrei.teachfinder.entities.ApplicationUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.security.auth.login.LoginContext;
import java.io.Console;
import java.io.IOException;

import static fr.efrei.teachfinder.utils.Constants.*;

public class HelloServlet extends HttpServlet {

    public void init() {

    }

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }
}