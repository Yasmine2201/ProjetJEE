package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IUserDAO;
import fr.efrei.teachfinder.entities.ApplicationUser;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class UserService implements IUserService {

    @Inject
    private IUserDAO userDAO;

    @Override
    public ApplicationUser getUser(int userId) throws EntityNotFoundException {
        ApplicationUser user = userDAO.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }

        return user;
    }

    @Override
    public void updateUser(ApplicationUser user) throws EntityNotFoundException {
        userDAO.update(user);
    }

    @Override
    public boolean userWithLoginExists(String login) {
        return userDAO.findByLogin(login) != null;
    }
}
