package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.entities.SessionUser;


public interface ISecurityService {

    String hashPassword(String password);

    SessionUser authentificate(String login, String password);

    boolean checkAuthorization(RoleType role, String action);
}
