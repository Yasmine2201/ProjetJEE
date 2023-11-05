package fr.efrei.teachfinder;

import fr.efrei.teachfinder.annotations.Action;
import fr.efrei.teachfinder.beans.RegistrationBean;
import fr.efrei.teachfinder.beans.SessionUser;
import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.exceptions.UnavailableLoginException;
import fr.efrei.teachfinder.services.IRegistrationService;
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
import java.util.Collections;

import static fr.efrei.teachfinder.utils.Constants.*;

public class Controller extends HttpServlet {

    @EJB
    private ISecurityService securityService;

    @EJB
    private IRegistrationService registrationService;

    private static final Logger log = LogManager.getLogger(Controller.class);

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

    public void useParametersAsAttributes(HttpServletRequest request, HttpServletResponse response) {
        for (String name : Collections.list(request.getParameterNames())) {
            request.setAttribute(name, request.getParameter(name));
        }
    }

    @Action(action = Actions.LOGIN)
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check credentials
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        SessionUser sessionUser = securityService.authentificate(login, password);

        if (sessionUser == null) {
            request.setAttribute("errorMessage", Messages.CREDENTIALS_KO);
            request.setAttribute("login", login);
            goToLogin(request, response);
            return;
        }

        // Create session
        HttpSession session = request.getSession(true);
        session.setAttribute("sessionUser", sessionUser);
        goToHome(request, response);
    }

    @Action(action = Actions.LOGOUT)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        goToLogin(request, response);
    }

    @Action(action = Actions.GO_TO_HOME)
    public void goToHome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionUser sessionUser = getSessionUser(request);

        String action;

        if (sessionUser == null)
        {
            action = Actions.GO_TO_LOGIN;
        } else {
            switch (sessionUser.getRole()) {
                case Admin -> action = Actions.GO_TO_ADMIN_HOME;
                case Recruiter -> action = Actions.GO_TO_RECRUITER_HOME;
                case Teacher -> action = Actions.GO_TO_TEACHER_HOME;
                default -> action = Actions.GO_TO_LOGIN;
            }
        }

        doAction(action, request, response);
    }

    @Action(action = Actions.GO_TO_LOGIN)
    public void goToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
    }

    @Action(action = Actions.GO_TO_ADMIN_HOME, roles = {RoleType.Admin})
    public void goToAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.ADMIN_HOME).forward(request, response);
    }

    @Action(action = Actions.GO_TO_RECRUITER_HOME, roles = {RoleType.Recruiter})
    public void goToRecruiterHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.RECRUITER_HOME).forward(request, response);
    }

    @Action(action = Actions.GO_TO_TEACHER_HOME, roles = {RoleType.Teacher})
    public void goToTeacherHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.TEACHER_HOME).forward(request, response);
    }

    @Action(action= Actions.GO_TO_REGISTER)
    public void goToRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.REGISTRATION).forward(request, response);
    }

    @Action(action = Actions.REGISTER)
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("password") != null &&
                !request.getParameter("password").equals(request.getParameter("passwordVerification"))) {
            request.setAttribute("errorMessage", Messages.PASSWORD_MISMATCH);
            useParametersAsAttributes(request, response);
            goToRegister(request, response);
            return;
        }

        RegistrationBean registration = new RegistrationBean();
        registration.setLogin(request.getParameter("login"));
        registration.setPassword(request.getParameter("password"));
        registration.setFirstname(request.getParameter("firstname"));
        registration.setLastname(request.getParameter("lastname"));
        registration.setEmail(request.getParameter("email"));
        registration.setPhone(request.getParameter("phone"));
        registration.setRole(request.getParameter("role"));
        registration.setSchoolName(request.getParameter("schoolName"));

        String errorMessage = null;

        try {
            registrationService.createRegistration(registration);
        } catch (IncompleteEntityException e) {
            errorMessage = Messages.MISSING_FIELD;
        } catch (UnavailableLoginException e) {
            errorMessage = Messages.UNAVAILABLE_LOGIN;
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (errorMessage != null) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", errorMessage);
        } else {
            request.setAttribute("successMessage", Messages.SUCCESSFUL_REGISTRATION);
        }

        goToRegister(request, response);
    }
}