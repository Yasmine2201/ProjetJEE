package fr.efrei.teachfinder.utils;

public final class Constants {

    // region config
    public static final String PERSISTENCE_UNIT_NAME = "default";
    // endregion

    // region pages
    public static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    public static final String INSCRIPTION_PAGE = "/WEB-INF/subscribe.jsp";
    public static final String ADMIN_HOME_PAGE = "/WEB-INF/admin/home.jsp";
    public static final String TEACHER_HOME_PAGE = "/WEB-INF/teacher/home.jsp";
    public static final String RECRUITER_HOME_PAGE = "/WEB-INF/recruiter/home.jsp";
    // endregion

    // region Error messages
    public static final String CREDENTIALS_KO_ERROR = "Identifiants invalides.";
    public static final String MISSING_FIELD_ERROR = "Un champ requis du formulaire n'est pas renseigné.";
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
    public static final String TEACHER_FINDBYID= "SELECT t from Teacher t WHERE t.teacherId = :teacherId";
    public static final String TEACHER_GETALL= "SELECT * from Teacher";
    public static final String RECRUITER_FINDBYID= "SELECT r from Recruiter r WHERE r.recruiterId= :recruiterId";
    public static final String FINDALL_RECRUITERS = "SELECT r FROM Recruiter r WHERE r.schoolName = :schoolName" ;
    public static final String SCHOOL_GETALL = "SELECT * from School";

    public static final String NEED_FINDBYID = "SELECT n from Need n WHERE n.needId = :needId" ;
    public static final String FINDALL_BY_SCHOOL = "SELECT n from Nedd WHERE n.schoolName= :schoolName" ;
    public static final String FINDALL_BY_RECRUITER = "SELECT n from Need WHERE n.recruiterId= :recruiterId";
    public static final String NEED_GETALL = "SELECT * from Need";
    public static final String NEED_SEARCHWITH_STRING = "SELECT n FROM Need n WHERE n.schoolName LIKE :search OR n.subject LIKE :search" ;
}
