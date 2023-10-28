package fr.efrei.teachfinder.servlets;

import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.entities.SessionUser;
import fr.efrei.teachfinder.services.ISecurityService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static fr.efrei.teachfinder.utils.Constants.*;

@WebServlet(urlPatterns = {"/login"}, name = "loginServlet")
public class LoginServlet extends HttpServlet {

    @EJB
    ISecurityService securityService;

    public void init() {
    }

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login"
                    -> doLogin(request, response);
            case "goToRegistration"
                    -> request.getRequestDispatcher(INSCRIPTION_PAGE).forward(request, response);
        }
    }

    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        SessionUser authenticatedUser = securityService.authentificate(login, password);
        if (authenticatedUser != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", authenticatedUser);
            redirectToHome(request, response, authenticatedUser.getRole());
        } else {
            request.setAttribute("errorMessage", CREDENTIALS_KO_ERROR);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    public void redirectToHome(HttpServletRequest request, HttpServletResponse response, RoleType role) throws ServletException, IOException {
        String page;

        switch (role) {
            case Admin -> page = ADMIN_HOME_PAGE;
            case Recruiter -> page = RECRUITER_HOME_PAGE;
            case Teacher -> page = TEACHER_HOME_PAGE;
            default -> page = LOGIN_PAGE;
        }

        request.getRequestDispatcher(page).forward(request, response);
    }
}
