package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.annotations.Action;
import fr.efrei.teachfinder.dao.IUserDAO;
import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.entities.SessionUser;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Stateless
public class SecurityService implements ISecurityService {

    private final MessageDigest digest;
    {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @EJB
    IUserDAO userDAO;

    public String hashPassword(String password) {
        byte[] hasedBytes = digest.digest(password.getBytes(StandardCharsets.ISO_8859_1));
        StringBuilder hasedStringBuilder = new StringBuilder(2 * hasedBytes.length);
        for (byte b : hasedBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hasedStringBuilder.append('0');
            }
            hasedStringBuilder.append(hex);
        }
        return hasedStringBuilder.toString();
    }

    public SessionUser authentificate(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            return null;
        }

        ApplicationUser user = userDAO.findByLogin(login);
        return (user != null && hashPassword(password).equals(user.getPassword())) ? new SessionUser(user) : null;
    }

    public boolean checkAuthorization(Method method, SessionUser sessionUser) {
        if (method == null) {
            return false;
        }

        List<RoleType> roles = Arrays.asList(method.getAnnotation(Action.class).roles());
        return (roles.isEmpty() || roles.contains(sessionUser.getRole()));
    }
}
