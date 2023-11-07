package fr.efrei.teachfinder;

import fr.efrei.teachfinder.annotations.Action;
import fr.efrei.teachfinder.beans.SessionUser;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.exceptions.MissingParameterException;
import fr.efrei.teachfinder.services.*;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.HashSet;

import static fr.efrei.teachfinder.entities.RoleType.*;
import static fr.efrei.teachfinder.utils.Constants.*;


public class Controller extends HttpServlet {

    @EJB private ICandidatureService candidatureService;
    @EJB private IDisponibilityService disponibilityService;
    @EJB private IEvaluationService evaluationService;
    @EJB private INeedService needService;
    @EJB private IRecruiterDashboardService recruiterDashboardService;
    @EJB private IRegistrationService registrationService;
    @EJB private IResearchService researchService;
    @EJB private ISchoolService schoolService;
    @EJB private ISecurityService securityService;
    @EJB private ITeacherDashboardService teacherDashboardService;
    @EJB private ITeacherService teacherService;
    @EJB private IUserService userService;
    @EJB private IRecruiterService recruiterService;

    private static final Logger log = LogManager.getLogger(Controller.class);

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        dispatch(action, request, response);
    }

    public void dispatch(String action, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        String httpMethod = request.getMethod();

        Method method = findActionMethod(action);
        HttpSession session = request.getSession(false);
        SessionUser sessionUser = getSessionUser(request);

        log.info("HTTP request. \n\tSERVLET: " + path +
                "\n\tMETHOD: " + httpMethod +
                "\n\tACTION: " + action +
                "\n\tUSER: " + sessionUser
        );

        if (action == null) {
            goToLogin(request, response);
            return;
        }

        if (isActionRestricted(action)
            && (sessionUser == null || session == null || !session.getId().equals(sessionUser.getSessionId()))
        ) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
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

    public SessionUser sendSessionUser(HttpServletRequest request) {
        SessionUser sessionUser = getSessionUser(request);

        if (sessionUser != null) {
            try {
                int userId = sessionUser.getUserId();

                switch (sessionUser.getRole()) {
                    case Teacher
                        -> request.setAttribute("teacher", teacherService.getTeacher(userId));
                    case Recruiter
                        -> request.setAttribute("recruiter", recruiterService.getRecruiter(userId));
                }
            } catch (EntityNotFoundException e) {
                log.error("User not found\n" + e.getMessage());
            }
        }

        request.setAttribute("sessionuser", sessionUser);
        return sessionUser;
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
        sessionUser.setSessionId(session.getId());
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

        if (sessionUser == null) {
            action = Actions.GO_TO_LOGIN;
        } else {
            switch (sessionUser.getRole()) {
                case Admin -> action = Actions.GO_TO_ADMIN_HOME;
                case Recruiter -> action = Actions.GO_TO_RECRUITER_HOME;
                case Teacher -> action = Actions.GO_TO_TEACHER_HOME;
                default -> action = Actions.GO_TO_LOGIN;
            }
        }

        dispatch(action, request, response);
    }

    @Action(action = Actions.GO_TO_LOGIN)
    public void goToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
    }

    @Action(action = Actions.GO_TO_ADMIN_HOME, roles = {Admin})
    public void goToAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.setAttribute("pendingRegistrations", registrationService.getPendingRegistrations());
        request.getRequestDispatcher(Pages.ADMIN_HOME).forward(request, response);
    }

    @Action(action = Actions.GO_TO_RECRUITER_HOME, roles = {Recruiter})
    public void goToRecruiterHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser sessionUser = sendSessionUser(request);
        request.setAttribute("runningNeeds", recruiterDashboardService.getRunningNeed(sessionUser.getUserId()));
        request.setAttribute("pendingCandidatures", recruiterDashboardService.getCandidatures(sessionUser.getUserId()));
        request.getRequestDispatcher(Pages.RECRUITER_HOME).forward(request, response);
    }

    @Action(action = Actions.GO_TO_TEACHER_HOME, roles = {Teacher})
    public void goToTeacherHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser sessionUser = sendSessionUser(request);
        request.setAttribute("interestingNeeds", teacherDashboardService.getInterestingNeeds(sessionUser.getUserId()));
        request.setAttribute("candidatures", teacherDashboardService.getCandidatures(sessionUser.getUserId()));
        request.getRequestDispatcher(Pages.TEACHER_HOME).forward(request, response);
    }

    @Action(action = Actions.GO_TO_REGISTER)
    public void goToRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.getRequestDispatcher(Pages.REGISTRATION).forward(request, response);
    }

    @Action(action = Actions.APPROVE_REGISTRATION, roles = {Admin})
    public void approveRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int registrationId = Integer.parseInt(request.getParameter("registrationId"));
            registrationService.approveRegistration(registrationId);
            request.setAttribute("message", Messages.SUCCESS);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "registrationId should be an integer");
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", Messages.UNAVAILABLE_ENTITY);
        } catch (EntityExistsException e) {
            request.setAttribute("message", Messages.UNAVAILABLE_LOGIN);
        }

        goToAdminHome(request, response);
    }

    @Action(action = Actions.DENY_REGISTRATION, roles = {Admin})
    public void denyRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int registrationId = Integer.parseInt(request.getParameter("registrationId"));
            registrationService.denyRegistration(registrationId);
            request.setAttribute("message", Messages.SUCCESS);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "registrationId should be an integer");
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", Messages.UNAVAILABLE_ENTITY);
        }

        goToAdminHome(request, response);
    }

    @Action(action = Actions.GO_TO_SCHOOL, roles = {Admin, Recruiter, Teacher})
    public void goToSchool(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        String schoolName = request.getParameter("schoolName");
        if (schoolName == null || StringUtils.isNullOrEmpty(schoolName)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            School school = schoolService.getSchool(schoolName);
            request.setAttribute("school", school);
            request.setAttribute("runningNeeds", schoolService.getSchoolRunningNeeds(schoolName));
            request.setAttribute("recruiters", schoolService.getSchoolRecruiters(schoolName));
            request.getRequestDispatcher(Pages.SCHOOL_VIEW).forward(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "School not found");
        }
    }

    @Action(action = Actions.GO_TO_SCHOOL_CREATION, roles = {Admin})
    public void goToSchoolCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.getRequestDispatcher(Pages.SCHOOL_EDIT).forward(request, response);
    }

    @Action(action = Actions.GO_TO_SCHOOL_EDITION, roles = {Admin})
    public void goToSchoolEdition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        String schoolName = request.getParameter("schoolName");
        if (schoolName == null || StringUtils.isNullOrEmpty(schoolName)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            School school = schoolService.getSchool(schoolName);
            request.setAttribute("school", school);
            request.getRequestDispatcher(Pages.SCHOOL_EDIT).forward(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_NEED, roles = {Admin, Recruiter, Teacher})
    public void goToNeed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            String needIdStr = request.getParameter("needId");
            if (needIdStr == null) {
                throw new MissingParameterException("Parameter 'needId' is missing");
            }
            int needId = Integer.parseInt(needIdStr);

            Need need = needService.getNeed(needId);

            // Candidatures are sent only to recruiter who manage the need or an admin. Otherwise, it is hidden
            if ((user.getRole() == Recruiter && need.getRecruiter().getId() == user.getUserId())
                || user.getRole() == Admin)
            {
                request.setAttribute("candidatures", need.getCandidatures());
            }

            need.setCandidatures(new HashSet<>());
            request.setAttribute("need", need);
            request.getRequestDispatcher(Pages.NEED_VIEW).forward(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_NEED_EDITION, roles = {Admin, Recruiter})
    public void goToNeedEdition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            String needIdStr = request.getParameter("needId");
            if (needIdStr == null) {
                throw new MissingParameterException("Parameter 'needId' is missing");
            }
            int needId = Integer.parseInt(needIdStr);

            Need need = needService.getNeed(needId);
            request.setAttribute("need", need);
            request.getRequestDispatcher(Pages.NEED_VIEW).forward(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_CANDIDATURE, roles = {Admin, Teacher, Recruiter})
    public void goToCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);

        try {
            String teacherIdStr = request.getParameter("teacherId");
            String needIdStr = request.getParameter("needId");

            if (teacherIdStr == null) {
                throw new MissingParameterException("Parameter 'teacherIdStr' is missing");
            } else if (needIdStr == null) {
                throw new MissingParameterException("Parameter 'needId' is missing");
            }

            CandidatureId candidatureId = new CandidatureId();
            candidatureId.setTeacherId(Integer.parseInt(teacherIdStr));
            candidatureId.setNeedId(Integer.parseInt(needIdStr));

            Candidature candidature = candidatureService.getCandidature(candidatureId);
            request.setAttribute("candidature", candidature);
            request.getRequestDispatcher(Pages.CANDIDATURE).forward(request, response);

        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
    }
}