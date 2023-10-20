package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IUserDAO;
import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.entities.SessionUser;
import fr.efrei.teachfinder.utils.IHashStrategy;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class SecurityService implements ISecurityService {

    @EJB
    IHashStrategy hashStrategy;

    @EJB
    IUserDAO userDAO;

    @Override
    public String hashPassword(String password) {
        return hashStrategy.hashString(password);
    }

    @Override
    public SessionUser authentificate(String login, String password) {
        ApplicationUser user = userDAO.findByLogin(login);
        return (user != null && hashPassword(password).equals(user.getPassword())) ? new SessionUser(user) : null;
    }

    @Override
    public boolean checkAuthorization(RoleType role, String action) {
        return true;
    }
}
