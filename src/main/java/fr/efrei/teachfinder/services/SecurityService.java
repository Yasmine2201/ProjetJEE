package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.ApplicationUser;

public class SecurityService implements ISecurityService {

    @Override
    public String hashPassword(String password) {
        return null;
    }

    @Override
    public boolean authentificate(String login, String password) {
        return false;
    }

    @Override
    public boolean checkAuthorization(ApplicationUser.Role role, String action) {
        return false;
    }
}
