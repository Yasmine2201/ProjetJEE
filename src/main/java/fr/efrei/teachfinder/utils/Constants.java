package fr.efrei.teachfinder.utils;

public final class Constants {

    // region temporaire
    public static final String ERROR_PAGE = "/WEB-INF/restricted/error.jsp";

    // region config
    public static final String PERSISTENCE_UNIT_NAME = "default";
    // endregion

    // region actions
    public static final String LOGIN_ACTION = "login";
    public static final String LOGOUT_ACTION = "logout";
    public static final String REGISTER_ACTION = "register";

    public static final String GO_TO_LOGIN_PAGE_ACTION = "goToLogin";
    public static final String GO_TO_HOME_ACTION = "goToHome";
    public static final String GO_TO_ADMIN_HOME_ACTION = "goToAdminHome";
    public static final String GO_TO_RECRUITER_HOME_ACTION = "goToRecruiterHome";
    public static final String GO_TO_TEACHER_HOME_ACTION = "goToTeacherHome";
    public static final String GO_TO_REGISTER_PAGE_ACTION = "goToRegister";
    // endregion actions

    // region pages
    public static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    public static final String REGISTRATION_PAGE = "/WEB-INF/subscribe.html";
    public static final String ADMIN_HOME_PAGE = "/WEB-INF/restricted/admin/home.jsp";
    public static final String TEACHER_HOME_PAGE = "/WEB-INF/restricted/teacher/home.jsp";
    public static final String RECRUITER_HOME_PAGE = "/WEB-INF/restricted/recruiter/home.jsp";
    // endregion

    // region Error messages
    public static final String CREDENTIALS_KO_ERROR = "Identifiants invalides.";
    public static final String FORBIDDEN_ERROR = "Vous n'êtes pas autorisé à réaliser cette action.";
    // endregion

    // region JPQL requests

    // ApplicationUser
    public static final String APPLICATIONUSER_FINDBYID = "SELECT u from ApplicationUser u WHERE u.userId = :id";
    public static final String APPLICATIONUSER_FINDBYLOGIN = "SELECT u from ApplicationUser u WHERE u.login = :login";

    // Registration
    public static final String REGISTRATION_FINDBYID = "SELECT r from Registration r WHERE r.registrationId = :registrationId";
    public static final String REGISTRATION_FINDBYLOGIN = "SELECT r from Registration r WHERE r.login = :login";
    public static final String REGISTRATION_GETALLWITHSTATUS = "SELECT r from Registration r WHERE r.status = :status";

    // School
    public static final String SCHOOL_FINDBYNAME = "SELECT s from School s WHERE s.schoolName = :schoolName ";
    // endregion
}
