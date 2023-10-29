package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.SessionUser;

import java.lang.reflect.Method;

public interface ISecurityService {

    String hashPassword(String password);

    SessionUser authentificate(String login, String password);

    boolean checkAuthorization(Method method, SessionUser sessionUser);
}
