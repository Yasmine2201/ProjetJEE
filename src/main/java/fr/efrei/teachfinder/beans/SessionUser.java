package fr.efrei.teachfinder.beans;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.RoleType;

public class SessionUser {

    private final int userId;
    private final String login;
    private final RoleType role;
    private final String firstName;
    private final String lastName;

    public SessionUser(ApplicationUser user) {
        userId = user.getId();
        login = user.getLogin();
        role = user.getRole();
        firstName = user.getFirstname();
        lastName = user.getLastname();
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}