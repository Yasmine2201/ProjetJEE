package fr.efrei.teachfinder.beans;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.RoleType;

public class SessionUser {

    private final int userId;
    private final String login;
    private final RoleType role;
    private final String firstname;
    private final String lastname;

    public SessionUser(ApplicationUser user) {
        userId = user.getId();
        login = user.getLogin();
        role = user.getRole();
        firstname = user.getFirstname();
        lastname = user.getLastname();
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

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}