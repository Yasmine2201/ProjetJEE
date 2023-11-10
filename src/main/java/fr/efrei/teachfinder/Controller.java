package fr.efrei.teachfinder;

import fr.efrei.teachfinder.annotations.Action;
import fr.efrei.teachfinder.beans.DisponibilityBean;
import fr.efrei.teachfinder.beans.NeedBean;
import fr.efrei.teachfinder.beans.SessionUser;
import fr.efrei.teachfinder.beans.TeacherBean;
import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.Teacher;
import fr.efrei.teachfinder.entities.*;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.exceptions.MissingParameterException;
import fr.efrei.teachfinder.services.*;
import fr.efrei.teachfinder.utils.StringUtils;
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
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static fr.efrei.teachfinder.entities.RoleType.*;
import static fr.efrei.teachfinder.utils.Constants.*;


public class Controller extends HttpServlet {

    @EJB
    private CandidatureService candidatureService;
    @EJB
    private DisponibilityService disponibilityService;
    @EJB
    private EvaluationService evaluationService;
    @EJB
    private NeedService needService;
    @EJB
    private RecruiterDashboardService recruiterDashboardService;
    @EJB
    private RegistrationService registrationService;
    @EJB
    private ResearchService researchService;
    @EJB
    private SchoolService schoolService;
    @EJB
    private SecurityService securityService;
    @EJB
    private TeacherDashboardService teacherDashboardService;
    @EJB
    private TeacherService teacherService;
    @EJB
    private UserService userService;
    @EJB
    private RecruiterService recruiterService;


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
        try {
            RequestWrapper req = new RequestWrapper(request);
            dispatch(req.getParameter("action"), req, response);
        } catch (Exception e) {
            log.error(e);
            request.getRequestDispatcher(Pages.ERROR_500).forward(request, response);
        }
    }

    public void dispatch(String action, RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
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

    public SessionUser getSessionUser(RequestWrapper request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;

        return (SessionUser) session.getAttribute("sessionUser");
    }

    public SessionUser sendSessionUser(RequestWrapper request) {
        SessionUser sessionUser = getSessionUser(request);

        if (sessionUser != null) {
            try {
                int userId = sessionUser.getUserId();

                switch (sessionUser.getRole()) {
                    case Teacher -> request.setAttribute("teacher", teacherService.getTeacher(userId));
                    case Recruiter -> request.setAttribute("recruiter", recruiterService.getRecruiter(userId));
                }
            } catch (EntityNotFoundException e) {
                log.error("User not found\n" + e.getMessage());
            }
        }

        request.setAttribute("sessionuser", sessionUser);
        return sessionUser;
    }

    public int getIntParameter(RequestWrapper request, String parameter) throws MissingParameterException, NumberFormatException {
        String strValue = request.getParameter(parameter);

        if (strValue == null) {
            throw new MissingParameterException("Parameter '" + parameter + "' is missing");
        }

        return Integer.parseInt(strValue);
    }

    public String getStringParameter(RequestWrapper request, String parameter) throws MissingParameterException {
        String strValue = request.getParameter(parameter);

        if (StringUtils.isNullOrEmpty(strValue)) {
            throw new MissingParameterException("Parameter '" + parameter + "' is missing");
        }

        return strValue;
    }

    public void useParametersAsAttributes(RequestWrapper request, HttpServletResponse response) {
        for (String name : Collections.list(request.getParameterNames())) {
            request.setAttribute(name, request.getParameter(name));
        }
    }

    @Action(action = Actions.LOGIN)
    public void login(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Check credentials
            String login = getStringParameter(request, "login");
            String password = getStringParameter(request, "password");

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

        } catch (MissingParameterException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.MISSING_FIELD);
            goToLogin(request, response);
        }
    }

    @Action(action = Actions.LOGOUT)
    public void logout(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        goToLogin(request, response);
    }

    @Action(action = Actions.GO_TO_HOME)
    public void goToHome(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
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
    public void goToLogin(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.LOGIN).forward(request, response);
    }

    @Action(action = Actions.GO_TO_ADMIN_HOME, roles = {Admin})
    public void goToAdminHome(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.setAttribute("pendingRegistrations", registrationService.getPendingRegistrations());
        request.getRequestDispatcher(Pages.ADMIN_HOME).forward(request, response);
    }

    @Action(action = Actions.GO_TO_RECRUITER_HOME, roles = {Recruiter})
    public void goToRecruiterHome(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser sessionUser = sendSessionUser(request);

        try {
            request.setAttribute("runningNeeds", recruiterDashboardService.getRunningNeed(sessionUser.getUserId()));
            request.setAttribute("pendingCandidatures", recruiterDashboardService.getCandidatures(sessionUser.getUserId()));
            request.getRequestDispatcher(Pages.RECRUITER_HOME).forward(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_TEACHER_HOME, roles = {Teacher})
    public void goToTeacherHome(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser sessionUser = sendSessionUser(request);

        try {
            request.setAttribute("interestingNeeds", teacherDashboardService.getInterestingNeeds(sessionUser.getUserId()));
            request.setAttribute("candidatures", teacherDashboardService.getCandidatures(sessionUser.getUserId()));
            request.getRequestDispatcher(Pages.TEACHER_HOME).forward(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_REGISTER)
    public void goToRegister(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.getRequestDispatcher(Pages.REGISTRATION).forward(request, response);
    }

    @Action(action = Actions.APPROVE_REGISTRATION, roles = {Admin})
    public void approveRegistration(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int registrationId = getIntParameter(request, "registrationId");
            registrationService.approveRegistration(registrationId);
            request.setAttribute("message", Messages.SUCCESS);
        } catch (NumberFormatException | MissingParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "registrationId should be an integer");
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", Messages.UNAVAILABLE_ENTITY);
        } catch (EntityExistsException e) {
            request.setAttribute("message", Messages.UNAVAILABLE_LOGIN);
        }

        goToAdminHome(request, response);
    }

    @Action(action = Actions.DENY_REGISTRATION, roles = {Admin})
    public void denyRegistration(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int registrationId = getIntParameter(request, "registrationId");
            registrationService.denyRegistration(registrationId);
            request.setAttribute("message", Messages.SUCCESS);
        } catch (NumberFormatException | MissingParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "registrationId should be an integer");
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", Messages.UNAVAILABLE_ENTITY);
        }

        goToAdminHome(request, response);
    }

    @Action(action = Actions.GO_TO_SCHOOL, roles = {Admin, Recruiter, Teacher})
    public void goToSchool(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);

        try {
            String schoolName = getStringParameter(request, "schoolName");
            School school = schoolService.getSchool(schoolName);
            request.setAttribute("school", school);
            request.setAttribute("runningNeeds", schoolService.getSchoolRunningNeeds(schoolName));
            request.setAttribute("recruiters", schoolService.getSchoolRecruiters(schoolName));
            request.getRequestDispatcher(Pages.SCHOOL_VIEW).forward(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (MissingParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_SCHOOL_CREATION, roles = {Admin})
    public void goToSchoolCreation(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.getRequestDispatcher(Pages.SCHOOL_EDIT).forward(request, response);
    }

    @Action(action = Actions.GO_TO_SCHOOL_EDITION, roles = {Admin})
    public void goToSchoolEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);

        try {
            String schoolName = getStringParameter(request, "schoolName");
            School school = schoolService.getSchool(schoolName);
            request.setAttribute("school", school);
            request.getRequestDispatcher(Pages.SCHOOL_EDIT).forward(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (MissingParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_NEED, roles = {Admin, Recruiter, Teacher})
    public void goToNeed(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            int needId = getIntParameter(request, "needId");
            Need need = needService.getNeed(needId);

            // Candidatures are sent only to recruiter who manage the need or an admin. Otherwise, it is hidden
            if ((user.getRole() == Recruiter && need.getRecruiter().getId() == user.getUserId())
                    || user.getRole() == Admin) {
                request.setAttribute("candidatures", need.getCandidatures());
            } else {
                need.setCandidatures(new HashSet<>());
            }
            request.setAttribute("need", need);
            request.getRequestDispatcher(Pages.NEED_VIEW).forward(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_NEED_CREATION, roles = {Recruiter})
    public void goToNeedCreation(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.getRequestDispatcher(Pages.NEED_EDIT).forward(request, response);
    }

    @Action(action = Actions.GO_TO_NEED_EDITION, roles = {Recruiter})
    public void goToNeedEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);

        try {
            int needId = getIntParameter(request, "needId");
            Need need = needService.getNeed(needId);
            request.setAttribute("need", need);
            request.getRequestDispatcher(Pages.NEED_EDIT).forward(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_CANDIDATURE, roles = {Admin, Teacher, Recruiter})
    public void goToCandidature(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);

        try {
            CandidatureId candidatureId = new CandidatureId();
            candidatureId.setTeacherId(getIntParameter(request, "teacherId"));
            candidatureId.setNeedId(getIntParameter(request, "needId"));
            Candidature candidature = candidatureService.getCandidature(candidatureId);

            boolean canChoose =
                    user.getUserId() == candidatureId.getTeacherId()
                            || user.getUserId() == candidature.getNeed().getRecruiter().getId();

            request.setAttribute("canChoose", canChoose);
            request.setAttribute("candidature", candidature);
            request.getRequestDispatcher(Pages.CANDIDATURE).forward(request, response);

        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_TEACHER, roles = {Admin, Recruiter, Teacher})
    public void goToTeacher(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        try {
            int teacherId = getIntParameter(request, "teacherId");

            request.setAttribute("teacher", teacherService.getTeacher(teacherId));
            request.setAttribute("futureDisponibilities", teacherService.getTeacherFutureDisponibilities(teacherId));
            request.setAttribute("evaluations", teacherService.getTeacherEvaluations(teacherId));
            request.getRequestDispatcher(Pages.TEACHER_VIEW).forward(request, response);

        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_TEACHER_EDITION, roles = {Teacher})
    public void goToTeacherEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);
        try {
            int teacherId = getIntParameter(request, "teacherId");

            if (teacherId != user.getUserId()) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Current user does not own this entity.");
                return;
            }

            request.setAttribute("teacher", teacherService.getTeacher(teacherId));
            request.setAttribute("futureDisponibilities", teacherService.getTeacherFutureDisponibilities(teacherId));
            request.getRequestDispatcher(Pages.TEACHER_EDIT).forward(request, response);

        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_DISPONIBILITY_CREATION, roles = {Teacher})
    public void goToDisponibilityCreation(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.getRequestDispatcher(Pages.DISPONIBILITY).forward(request, response);
    }

    @Action(action = Actions.GO_TO_DISPONIBILITY_EDITION, roles = {Teacher})
    public void goToDisponibilityEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);
        try {
            int disponibilityId = getIntParameter(request, "disponibilityId");
            Disponibility disponibility = disponibilityService.getDisponibility(disponibilityId);

            if (disponibility.getTeacher().getId() != user.getUserId()) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Current user does not own this entity.");
                return;
            }

            request.setAttribute("disponibility", disponibility);
            request.getRequestDispatcher(Pages.DISPONIBILITY).forward(request, response);

        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_USER_PROFILE, roles = {Admin, Recruiter, Teacher})
    public void goToProfile(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);

        try {
            ApplicationUser applicationUser = userService.getUser(user.getUserId());

            // hide password
            applicationUser.setPassword("");
            request.setAttribute("user", applicationUser);
            request.getRequestDispatcher(Pages.USER_VIEW).forward(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_USER_PROFILE_EDITION, roles = {Admin, Recruiter, Teacher})
    public void goToProfileEdit(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);

        try {
            ApplicationUser applicationUser = userService.getUser(user.getUserId());

            // hide password
            applicationUser.setPassword("");
            request.setAttribute("user", applicationUser);
            request.getRequestDispatcher(Pages.USER_EDIT).forward(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_EVALUATION, roles = {Recruiter})
    public void goToEvaluation(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);

        try {
            EvaluationId evaluationId = new EvaluationId();
            evaluationId.setTeacherId(getIntParameter(request, "teacherId"));
            evaluationId.setSchoolName(getStringParameter(request, "schoolName"));

            Evaluation evaluation = evaluationService.getEvaluation(evaluationId);
            if (evaluation != null) {
                request.setAttribute("evaluation", evaluation);
            }
            request.getRequestDispatcher(Pages.EVALUATION).forward(request, response);

        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.GO_TO_RESEARCH, roles = {Admin, Recruiter, Teacher})
    public void goToResearch(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        sendSessionUser(request);
        request.getRequestDispatcher(Pages.RESEARCH).forward(request, response);
    }

    @Action(action = Actions.GO_TO_DISPONIBILITIES, roles = {Teacher})
    public void goToDisponibilities(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        SessionUser user = sendSessionUser(request);
        request.getRequestDispatcher(Pages.DISPONIBILITIES).forward(request, response);
    }

    @Action(action = Actions.CANCEL_SCHOOL_CREATION, roles = {Admin})
    public void cancelSchoolCreation(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToAdminHome(request, response);
    }

    @Action(action = Actions.CANCEL_SCHOOL_EDITION, roles = {Admin})
    public void cancelSchoolEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToSchool(request, response);
    }

    @Action(action = Actions.CANCEL_NEED_CREATION, roles = {Recruiter})
    public void cancelNeedCreation(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int recruiterId = getSessionUser(request).getUserId();
            Recruiter recruiter = recruiterService.getRecruiter(recruiterId);
            request.setParameter("schoolName", recruiter.getSchoolName().getSchoolName());
            goToSchool(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.CANCEL_NEED_EDITION, roles = {Recruiter})
    public void cancelNeedEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToNeed(request, response);
    }

    @Action(action = Actions.CANCEL_DISPONIBILITY_CREATION, roles = {Teacher})
    public void cancelDisponibilityCreation(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToTeacherHome(request, response);
    }

    @Action(action = Actions.CANCEL_DISPONIBILITY_EDITION, roles = {Teacher})
    public void cancelDisponibilityEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToTeacherHome(request, response);
    }

    @Action(action = Actions.CANCEL_EVALUATION_EDITION, roles = {Recruiter})
    public void cancelEvaluationEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToTeacherHome(request, response);
    }

    @Action(action = Actions.CANCEL_PROFILE_EDITION, roles = {Admin, Recruiter, Teacher})
    public void cancelProfileEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToProfile(request, response);
    }

    @Action(action = Actions.CANCEL_TEACHER_EDITION, roles = {Teacher})
    public void cancelTeacherEdition(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        goToTeacher(request, response);
    }

    @Action(action = Actions.CREATE_SCHOOL, roles = {Admin})
    public void createSchool(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String schoolName = request.getParameter("schoolName");
            String address = request.getParameter("address");
            String specializations = request.getParameter("specializations");

            School school = new School();
            school.setSchoolName(schoolName);
            school.setAddress(address);
            school.setSpecializations(specializations);
            school = schoolService.createSchool(school);
            request.setParameter("schoolName", school.getSchoolName());

            goToSchool(request, response);

        } catch (EntityExistsException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.SCHOOL_ALREADY_EXISTS);
            goToSchoolCreation(request, response);
        } catch (IncompleteEntityException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.MISSING_FIELD);
            goToSchoolCreation(request, response);
        }
    }

    @Action(action = Actions.UPDATE_SCHOOL, roles = {Admin})
    public void updateSchool(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String schoolName = getStringParameter(request, "schoolName");
            String address = request.getParameter("address");
            String specializations = request.getParameter("specializations");

            School school = schoolService.getSchool(schoolName);
            school.setAddress(address);
            school.setSpecializations(specializations);
            schoolService.updateSchool(school);

            goToSchool(request, response);

        } catch (IncompleteEntityException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.MISSING_FIELD);
            goToSchoolEdition(request, response);
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (MissingParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.CREATE_DISPONIBILITY, roles = {Teacher})
    public void createDisponibility(RequestWrapper request, HttpServletResponse response)
            throws IOException, ServletException, EntityExistsException {

        try {
            DisponibilityBean disponibility = new DisponibilityBean();
            disponibility.setTeacherId(getIntParameter(request, "teacherId"));
            disponibility.setStartDate(request.getParameter("startDate"));
            disponibility.setEndDate(request.getParameter("endDate"));

            disponibilityService.createDisponibility(disponibility);

            goToTeacher(request, response);
        } catch (IncompleteEntityException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.MISSING_FIELD);
            goToSchoolCreation(request, response);
        } catch (MissingParameterException | IllegalArgumentException | DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.UPDATE_DISPONIBILITY, roles = {Teacher})
    public void updateDisponibility(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DisponibilityBean disponibility = new DisponibilityBean();
            disponibility.setDisponibilityId(getIntParameter(request, "disponibilityId"));
            disponibility.setTeacherId(getIntParameter(request, "teacherId"));
            disponibility.setStartDate(request.getParameter("startDate"));
            disponibility.setEndDate(request.getParameter("endDate"));

            disponibilityService.editDisponibility(disponibility);

            goToTeacher(request, response);
        } catch (IncompleteEntityException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.MISSING_FIELD);
            goToSchoolCreation(request, response);
        } catch (MissingParameterException | IllegalArgumentException | DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }


    @Action(action = Actions.UPDATE_TEACHER, roles = {Teacher})
    public void updateTeacher(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int teacherId = Integer.parseInt(request.getParameter("teacherId"));
            String schoolInterests = request.getParameter("schoolInterests");
            String academicCertifications = request.getParameter("academicCertifications");
            String contractType = request.getParameter("contractType");
            String personnalInterests = request.getParameter("personnalInterests");
            String skills = request.getParameter("skills");
            String desiredLevels = request.getParameter("desiredLevels");
            String recommendations = request.getParameter("recommendations");
            String experiences = request.getParameter("experiences");
            String otherInformations = request.getParameter("otherInformations");

            Teacher teacher = teacherService.getTeacher(teacherId);

            TeacherBean teacherBean = new TeacherBean();
            teacherBean.setTeacherId(teacherId);
            teacherBean.setExperiences(experiences);
            teacherBean.setSkills(skills);
            teacherBean.setPersonnalInterests(personnalInterests);
            teacherBean.setSchoolInterests(schoolInterests);
            teacherBean.setDesiredLevels(desiredLevels);
            teacherBean.setContractType(contractType);
            teacherBean.setAcademicCertifications(academicCertifications);
            teacherBean.setRecommendations(recommendations);
            teacherBean.setOtherInformations(otherInformations);


            Teacher modifyTeacher = teacherService.updateTeacher(teacherBean);
            request.getSession().setAttribute("message", "Enseignant mis à jour avec succès.");
            request.setParameter("teacherId", modifyTeacher.getId().toString());
            goToTeacher(request, response);
        } catch (EntityNotFoundException ex) {
            // Gérer l'exception selon vos besoins, par exemple, rediriger vers une page d'erreur
            throw new RuntimeException("Erreur lors de la mise à jour de l'enseignant : " + ex.getMessage(), ex);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @Action(action = Actions.CREATE_NEED, roles = {Recruiter})
    public void createNeed(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException, EntityExistsException {
        SessionUser user = getSessionUser(request);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            Recruiter recruiter = recruiterService.getRecruiter(user.getUserId());

            NeedBean needBean = new NeedBean();
            needBean.setRecruiterId(recruiter.getId());
            needBean.setSchoolName(recruiter.getSchoolName().getSchoolName());
            needBean.setSubject(request.getParameter("subject"));
            needBean.setContractType(request.getParameter("contractType"));
            needBean.setRequirements(request.getParameter("requirements"));
            needBean.setTimePeriod(request.getParameter("timePeriod"));
            needBean.setNotes(request.getParameter("notes"));

            Need need = needService.createNeed(needBean);
            request.setParameter("needId", need.getId().toString());

            goToNeed(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (IncompleteEntityException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.MISSING_FIELD);
            goToNeedCreation(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.UPDATE_NEED, roles = {Recruiter})
    public void updateNeed(RequestWrapper request, HttpServletResponse response) throws IOException, ServletException {
        SessionUser user = getSessionUser(request);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            int needId = getIntParameter(request, "needId");
            Recruiter recruiter = recruiterService.getRecruiter(user.getUserId());

            NeedBean needBean = new NeedBean();
            needBean.setNeedId(needId);
            needBean.setRecruiterId(recruiter.getId());
            needBean.setSchoolName(recruiter.getSchoolName().getSchoolName());
            needBean.setSubject(request.getParameter("subject"));
            needBean.setContractType(request.getParameter("contractType"));
            needBean.setRequirements(request.getParameter("requirements"));
            needBean.setTimePeriod(request.getParameter("timePeriod"));
            needBean.setNotes(request.getParameter("notes"));

            Need need = needService.updateNeed(needBean);
            request.setParameter("needId", need.getId().toString());

            goToNeed(request, response);

        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (IncompleteEntityException e) {
            useParametersAsAttributes(request, response);
            request.setAttribute("errorMessage", Messages.MISSING_FIELD);
            goToNeedCreation(request, response);
        } catch (IllegalArgumentException | MissingParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Action(action = Actions.APPLY, roles = {Teacher})
    public void applyToNeed(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int teacherId = getSessionUser(request).getUserId();
            int needId = getIntParameter(request, "needId");
            Candidature candidature = needService.apply(needId, teacherId);
            request.setParameter("teacherId", candidature.getId().getTeacherId().toString());
            request.setParameter("needId", candidature.getId().getNeedId().toString());
            goToCandidature(request, response);
        } catch (EntityExistsException e) {
            request.setAttribute("errorMessage", Messages.CANDIDATURE_ALREADY_EXISTS);
            goToNeed(request, response);
        } catch (MissingParameterException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (EntityNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Action(action = Actions.DENY_CANDIDATURE, roles = {Teacher, Recruiter})
    public void denyCandidature(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SessionUser user = getSessionUser(request);
            CandidatureId candidatureId = new CandidatureId();
            candidatureId.setTeacherId(getIntParameter(request, "teacherId"));
            candidatureId.setNeedId(getIntParameter(request, "needId"));
            Candidature candidature = candidatureService.getCandidature(candidatureId);

            switch (user.getRole()) {
                case Teacher :
                    candidatureService.refuseByTeacher(candidatureId,user);
                    request.setAttribute("message","Candidature refusée avec succès");
                    goToCandidature(request,response);

                case Recruiter :
                    candidatureService.refuseByRecruiter(candidatureId, user);
                    request.setAttribute("messagee","Candidature refusée avec succès");
                    goToCandidature(request,response);

                }
        }
    catch (
            MissingParameterException | EntityNotFoundException | IllegalAccessException e) {
        throw new RuntimeException(e);

    }

    }

    @Action(action = Actions.VALIDATE_CANDIDATURE, roles = {Teacher, Recruiter})
    public void validateCandidature(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SessionUser user = getSessionUser(request);
            CandidatureId candidatureId = new CandidatureId();
            candidatureId.setTeacherId(getIntParameter(request, "teacherId"));
            candidatureId.setNeedId(getIntParameter(request, "needId"));
            Candidature candidature = candidatureService.getCandidature(candidatureId);

            switch (user.getRole()) {
                case Teacher :
                    candidatureService.acceptByTeacher(candidatureId,user);
                    request.setAttribute("message","Candidature acceptée avec succès par l'enseignant");
                    goToCandidature(request,response);

                case Recruiter :
                    candidatureService.acceptByRecruiter(candidatureId, user);
                    request.setAttribute("messagee","Candidature acceptée avec succès par le recruteur");
                    goToCandidature(request,response);

            }
        }
        catch (
                MissingParameterException | EntityNotFoundException | IllegalAccessException e) {
            throw new RuntimeException(e);

        }

    }


    @Action(action = Actions.RESEARCH, roles = {Admin, Recruiter, Teacher})
    public void research(RequestWrapper request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String researchText = getStringParameter(request, "researchText");
            String researchType = getStringParameter(request, "researchType");

            switch (researchType) {
                case "Need" ->
                    request.setAttribute("needs", researchService.researchNeed(researchText));
                case "Teacher" ->
                    request.setAttribute("teachers", researchService.researchSkills(researchText));
                case "School" ->
                    request.setAttribute("schools", researchService.researchSchool(researchText));
                default ->
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid research type");
            }

            useParametersAsAttributes(request, response);
            goToResearch(request, response);

        } catch (MissingParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}