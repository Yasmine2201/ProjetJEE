package fr.efrei.teachfinder;

import fr.efrei.teachfinder.annotations.Action;
import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.entities.SessionUser;
import fr.efrei.teachfinder.services.ISecurityService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static fr.efrei.teachfinder.utils.Constants.*;

public class Controller extends HttpServlet {

    @EJB
    ISecurityService securityService;

    public static final Logger log = LogManager.getLogger(Controller.class);

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
        String action = request.getParameter("action");
        doAction(action, request, response);
    }

    public void doAction(String action, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        String httpMethod = request.getMethod();

        Method method = findActionMethod(action);
        SessionUser sessionUser = getSessionUser(request);

        log.info("HTTP request. \n\tSERVLET: " + path +
                "\n\tMETHOD: " + httpMethod +
                "\n\tACTION: " + action +
                "\n\tUSER: " + sessionUser
        );

        if (action == null || (isActionRestricted(action) && sessionUser == null)) {
            goToLogin(request, response);
            return;
        }

        if (method == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            if (securityService.checkAuthorization(method, sessionUser)) {
                method.invoke(this, request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (IllegalAccessException e) {
            throw new ServletException(e);
        } catch (InvocationTargetException e) {
            throw new ServletException(e.getCause());
        }
    }

    public Method findActionMethod(String action) {
        return Arrays.stream(getClass().getDeclaredMethods())
                .filter(m -> m.getAnnotation(Action.class) != null
                        && m.getAnnotation(Action.class).action().equals(action))
                .findFirst()
                .orElse(null);
    }

    public boolean isActionRestricted(String action) {
        Method method = findActionMethod(action);
        if (method == null)
            return true;

        return !Arrays.asList(method.getAnnotation(Action.class).roles()).isEmpty();
    }

    public SessionUser getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;

        return (SessionUser) session.getAttribute("sessionUser");
    }

    @Action(action = LOGIN_ACTION)
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check credentials
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        SessionUser sessionUser = securityService.authentificate(login, password);

        if (sessionUser == null) {
            request.setAttribute("errorMessage", CREDENTIALS_KO_ERROR);
            request.setAttribute("login", login);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }

        // Create session
        HttpSession session = request.getSession(true);
        session.setAttribute("sessionUser", sessionUser);
        goToHome(request, response);
    }

    @Action(action = LOGOUT_ACTION)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Action(action = GO_TO_HOME_ACTION)
    public void goToHome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionUser sessionUser = getSessionUser(request);

        String action;

        if (sessionUser == null)
        {
            action = GO_TO_LOGIN_ACTION;
        } else {
            switch (sessionUser.getRole()) {
                case Admin -> action = GO_TO_ADMIN_HOME_ACTION;
                case Recruiter -> action = GO_TO_RECRUITER_HOME_ACTION;
                case Teacher -> action = GO_TO_TEACHER_HOME_ACTION;
                default -> action = GO_TO_LOGIN_ACTION;
            }
        }

        doAction(action, request, response);
    }

    @Action(action = GO_TO_LOGIN_ACTION)
    public void goToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Action(action = GO_TO_ADMIN_HOME_ACTION, roles = {RoleType.Admin})
    public void goToAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ADMIN_HOME_PAGE).forward(request, response);
    }

    @Action(action = GO_TO_RECRUITER_HOME_ACTION, roles = {RoleType.Recruiter})
    public void goToRecruiterHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(RECRUITER_HOME_PAGE).forward(request, response);
    }

    @Action(action = GO_TO_TEACHER_HOME_ACTION, roles = {RoleType.Teacher})
    public void goToTeacherHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(TEACHER_HOME_PAGE).forward(request, response);
    }


}