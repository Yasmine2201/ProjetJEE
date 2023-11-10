package fr.efrei.teachfinder.utils;

public final class Constants {

    public static final String PERSISTENCE_UNIT_NAME = "default";

    public static final class Actions {
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String REGISTER = "register";
        public static final String APPLY = "applyToNeed";
        public static final String VALIDATE_CANDIDATURE = "validateCandidature";
        public static final String DENY_CANDIDATURE = "denyCandidature";
        public static final String CREATE_SCHOOL = "createSchool";
        public static final String UPDATE_SCHOOL = "updateSchool";
        public static final String CREATE_DISPONIBILITY = "createDisponibility";
        public static final String UPDATE_DISPONIBILITY = "updateDisponibility";
        public static final String CREATE_NEED = "createNeed";
        public static final String UPDATE_NEED = "updateNeed";
        public static final String UPDATE_USER = "updateUserProfile";
        public static final String UPDATE_TEACHER = "updateTeacher";
        public static final String UPSERT_EVALUATION = "upsertEvaluation";
        public static final String APPROVE_REGISTRATION = "approveRegistration";
        public static final String DENY_REGISTRATION = "denyRegistration";

        public static final String GO_TO_LOGIN = "goToLogin";
        public static final String GO_TO_HOME = "goToHome";
        public static final String GO_TO_ADMIN_HOME = "goToAdminHome";
        public static final String GO_TO_RECRUITER_HOME = "goToRecruiterHome";
        public static final String GO_TO_TEACHER_HOME = "goToTeacherHome";
        public static final String GO_TO_REGISTER = "goToRegistration";
        public static final String GO_TO_CANDIDATURE = "goToCandidature";
        public static final String GO_TO_DISPONIBILITY_CREATION = "goToDisponibilityCreation";
        public static final String GO_TO_DISPONIBILITY_EDITION = "goToDisponibilityEdition";
        public static final String GO_TO_EVALUATION = "goToEvaluation";
        public static final String GO_TO_NEED = "goToNeed";
        public static final String GO_TO_NEED_CREATION = "goToNeedCreation";
        public static final String GO_TO_NEED_EDITION = "goToNeedEdition";
        public static final String GO_TO_RESEARCH = "goToResearch";
        public static final String GO_TO_SCHOOL = "goToSchool";
        public static final String GO_TO_SCHOOL_CREATION = "goToSchoolCreation";
        public static final String GO_TO_SCHOOL_EDITION = "goToSchoolEdition";
        public static final String GO_TO_TEACHER = "goToTeacher";
        public static final String GO_TO_TEACHER_EDITION = "goToTeacherEdition";
        public static final String GO_TO_USER_PROFILE = "goToUserProfile";
        public static final String GO_TO_USER_PROFILE_EDITION = "goToUserProfileEdition";
        public static final String GO_TO_DISPONIBILITIES = "goToDisponibilities";

        public static final String CANCEL_SCHOOL_CREATION = "cancelSchoolCreation";
        public static final String CANCEL_SCHOOL_EDITION =  "cancelSchoolEdition";
        public static final String CANCEL_NEED_CREATION = "cancelNeedCreation";
        public static final String CANCEL_NEED_EDITION =  "cancelNeedEdition";
        public static final String CANCEL_DISPONIBILITY_CREATION = "cancelDisponibilityCreation";
        public static final String CANCEL_DISPONIBILITY_EDITION =  "cancelDisponibilityEdition";
        public static final String CANCEL_EVALUATION_EDITION = "cancelEvaluationEdition";
        public static final String CANCEL_TEACHER_EDITION = "cancelTeacherEdition";
        public static final String CANCEL_PROFILE_EDITION = "cancelProfileEdition";
    }

    public static final class Pages {
        public static final String LOGIN = "/WEB-INF/login.jsp";
        public static final String REGISTRATION = "/WEB-INF/subscribe.html";
        public static final String ADMIN_HOME = "/WEB-INF/restricted/admin/home.jsp";
        public static final String TEACHER_HOME = "/WEB-INF/restricted/teacher/home.jsp";
        public static final String RECRUITER_HOME = "/WEB-INF/restricted/recruiter/home.jsp";
        public static final String SCHOOL_VIEW = "/WEB-INF/school/readSchool.jsp";
        public static final String SCHOOL_EDIT = "/WEB-INF/school/editSchool.jsp";
        public static final String NEED_VIEW = "/WEB-INF/need/readNeed.jsp";
        public static final String NEED_EDIT = "/WEB-INF/need/editNeed.jsp";
        public static final String TEACHER_VIEW = "/WEB-INF/teacher/readTeacher.jsp";
        public static final String TEACHER_EDIT = "/WEB-INF/teacher/editTeacher.jsp";
        public static final String USER_VIEW = "/WEB-INF/user/readProfile.jsp";
        public static final String USER_EDIT = "/WEB-INF/user/editProfile.jsp";
        public static final String CANDIDATURE = "/WEB-INF/candidature/candidature.jsp";
        public static final String EVALUATION = "/WEB-INF/evaluation/evaluation.jsp";
        public static final String DISPONIBILITY = "/WEB-INF/restricted/teacher/disponibility.jsp";
        public static final String DISPONIBILITIES = "/WEB-INF/disponibility/disponibilities.jsp";
        public static final String RESEARCH = "/WEB-INF/research.jsp";
        public static final String ERROR_500 = "/WEB-INF/error500.jsp";
    }

    public static final class Messages {
        public static final String CREDENTIALS_KO = "Identifiants invalides";
        public static final String MISSING_FIELD = "Un champ requis du formulaire n'est pas renseigné";
        public static final String FORBIDDEN = "Vous n'êtes pas autorisé à réaliser cette action";
        public static final String PASSWORD_MISMATCH = "Mot de passe de confirmation non conforme";
        public static final String UNAVAILABLE_LOGIN = "Cet identifiant est déjà utilisé par un autre utilisateur";
        public static final String SCHOOL_ALREADY_EXISTS = "Cette école existe déjà";
        public static final String CANDIDATURE_ALREADY_EXISTS = "Vous avez déjà candidaté à cette offre";
        public static final String SUCCESSFUL_REGISTRATION = "Inscription envoyée";
        public static final String INVALID_ARGUMENT = "Argument invalide";
        public static final String UNAVAILABLE_ENTITY = "Entité non disponible";
        public static final String SUCCESS = "Succès de l'opération";
    }

    public static final class QueryRequests {
        public static final String APPLICATIONUSER_FINDBYID = "SELECT u from ApplicationUser u WHERE u.id = :id";
        public static final String APPLICATIONUSER_FINDBYLOGIN = "SELECT u from ApplicationUser u WHERE u.login = :login";

        public static final String REGISTRATION_FINDBYID = "SELECT r from Registration r WHERE r.registrationId = :registrationId";
        public static final String REGISTRATION_FINDBYLOGIN = "SELECT r from Registration r WHERE r.login = :login";
        public static final String REGISTRATION_GETALLWITHSTATUS = "SELECT r from Registration r WHERE r.status = :status";

        public static final String SCHOOL_FINDBYNAME = "SELECT s from School s WHERE s.schoolName = :schoolName";
        public static final String SCHOOL_GETALL = "SELECT s from School s";

        public static final String TEACHER_FINDBYID = "SELECT t from Teacher t WHERE t.id = :teacherId";
        public static final String TEACHER_GETALL = "SELECT t from Teacher t";

        public static final String RECRUITER_FINDBYID = "SELECT r from Recruiter r WHERE r.id = :recruiterId";
        public static final String RECRUITER_FINDALL_BY_SCHOOL = "SELECT r from Recruiter r WHERE r.schoolName.schoolName = :schoolName";

        public static final String NEED_FINDBYID = "SELECT n from Need n WHERE n.id = :needId";
        public static final String NEED_FINDALL_BY_SCHOOL = "SELECT n from Need n WHERE n.schoolName.schoolName = :schoolName";
        public static final String NEED_FINDALL_BY_RECRUITER = "SELECT n from Need n WHERE n.recruiter.id = :recruiterId";
        public static final String NEED_GETALL = "SELECT n from Need n";
        public static final String NEED_SEARCHWITH_STRING = "SELECT n FROM Need n WHERE n.schoolName.schoolName LIKE %:search% OR n.subject LIKE %:search%";

        public static final String CANDIDATURE_FINDBYID = "SELECT c FROM Candidature c WHERE c.teacher.id = :teacherId AND c.need.id = :needId";
        public static final String CANDIDATURE_FINDALL_BY_TEACHER = "SELECT c FROM Candidature c WHERE c.teacher.id = :teacherId";
        public static final String CANDIDATURE_FINDALL_BY_NEED = "SELECT c FROM Candidature c WHERE c.need.id = :needId";
        public static final String CANDIDATURE_FINDALL_BY_RECRUITER = "SELECT c FROM Candidature c WHERE c.need.recruiter.id = :recruiterId";

        public static final String EVALUATION_FINDBYID = "SELECT ev FROM Evaluation ev WHERE ev.schoolName.schoolName = :schoolName AND ev.teacher.id = :teacherId";
        public static final String EVALUATION_FINDALL_BY_TEACHER = "SELECT ev FROM Evaluation ev WHERE ev.teacher.id = :teacherId";

        public static final String DISPONIBILITY_FINDBYID = "SELECT d from Disponibility d WHERE d.id = :disponibilityId";
        public static final String DISPONIBILITY_FINDALL_BY_TEACHER = "SELECT d from Disponibility d WHERE d.teacher.id = :teacherId";
        public static final String SCHOOL_SEARCHWITH_STRING = "SELECT s FROM School s WHERE s.schoolName.schoolName LIKE %:search% OR s.address LIKE %:search% OR s.specializations LIKE %:search%";
        public static final String TEACHER_SEARCHWITH_SKILLS = " SELECT t FROM Teacher t WHERE t.skills LIKE %:search%";
    }
}
