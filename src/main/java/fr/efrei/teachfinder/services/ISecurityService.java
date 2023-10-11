package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.SessionUser;


public interface ISecurityService {

    String hashPassword(String password);

    SessionUser authentificate(String login, String password);

    boolean checkAuthorization(ApplicationUser.Role role, String action);
}
