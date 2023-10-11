package fr.efrei.teachfinder.utils;

public final class Constants {

    // region config
    public static final String PERSISTENCE_UNIT_NAME = "default";
    // endregion

    // region pages
    public static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    public static final String INSCRIPTION_PAGE = "/WEB-INF/subscribe.jsp";
    // endregion

    // region JSPL requests

    // ApplicationUser
    public static final String APPLICATIONUSER_FINDBYID = "SELECT u from ApplicationUser u WHERE u.userId = :id";
    public static final String APPLICATIONUSER_FINDBYLOGIN = "SELECT u from ApplicationUser u WHERE u.login = :login";

    // Registration
    public static final String REGISTRATION_FINDBYLOGIN = "SELECT r from Registration r WHERE r.login = :login";
    public static final String REGISTRATION_GETALLWITHSTATUS = "SELECT r from Registration r WHERE r.status = :status";

    // endregion
}
