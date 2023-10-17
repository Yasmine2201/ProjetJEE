package fr.efrei.teachfinder.entities;

public class SessionUser {

    private final int userId;
    private final String login;
    private final RoleType role;

    public SessionUser(ApplicationUser user) {
        userId = user.getId();
        login = user.getLogin();
        role = user.getRole();
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public RoleType getRole() {
        return role;
    }
}