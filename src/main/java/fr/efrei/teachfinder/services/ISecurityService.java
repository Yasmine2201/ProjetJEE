package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.ApplicationUser;


public interface ISecurityService {

    String hashPassword(String password);

    boolean authentificate(String login, String password);

    boolean checkAuthorization(ApplicationUser.Role role, String action);
}
