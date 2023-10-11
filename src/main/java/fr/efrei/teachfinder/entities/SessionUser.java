package fr.efrei.teachfinder.entities;

import fr.efrei.teachfinder.entities.ApplicationUser.Role;

public class SessionUser {

    private final int userId;
    private final String login;
    private final Role role;

    public SessionUser(ApplicationUser user) {
        userId = user.getUserId();
        login = user.getLogin();
        role = user.getRole();
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }
}
