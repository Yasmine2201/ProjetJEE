package fr.efrei.teachfinder;

import fr.efrei.teachfinder.entities.SessionUser;
import fr.efrei.teachfinder.services.ISecurityService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static fr.efrei.teachfinder.utils.Constants.*;

public class Controller extends HttpServlet {

    @EJB
    ISecurityService securityService;

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
        SessionUser sessionUser = checkSession(request);
        String action = request.getParameter("action");

        if (action != null && action.equals("login")) {
            loginAction(request, response);
            return;
        }

        if (sessionUser == null || action == null) {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }
        if (!securityService.checkAuthorization(sessionUser.getRole(), action)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        switch (action) {
            case "logout":
                logoutAction(request, response);
                break;
        }
    }

    public SessionUser checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;

        // Search JSessionId in Cookies
        Cookie[] cookies = request.getCookies();
        Optional<String> jSessionId = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("JSESSIONID"))
                .map(Cookie::getValue)
                .findAny();
        if (jSessionId.isEmpty() || !jSessionId.get().equals(session.getId())) return null;

        return (SessionUser) session.getAttribute("sessionUser");
    }

    public void loginAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", MISSING_FIELD_ERROR);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }

        SessionUser sessionUser = securityService.authentificate(login, password);

        if (sessionUser == null) {
            request.setAttribute("errorMessage", MISSING_FIELD_ERROR);
            request.setAttribute("login", login);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("sessionUser", sessionUser);
        redirectToHome(request, response);
    }

    public void logoutAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    public void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");

        String page;
        switch (sessionUser.getRole()) {
            case Admin -> page = ADMIN_HOME_PAGE;
            case Recruiter -> page = RECRUITER_HOME_PAGE;
            case Teacher -> page = TEACHER_HOME_PAGE;
            default -> page = LOGIN_PAGE;
        }

        request.getRequestDispatcher(page).forward(request, response);
    }
}