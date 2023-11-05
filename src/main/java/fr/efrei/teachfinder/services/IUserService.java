package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.Registration;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

public interface IUserService {

    ApplicationUser getUser(int userId) throws EntityNotFoundException;

    void updateUser(ApplicationUser user);

    boolean userWithLoginExists(String login);

    ApplicationUser createUserFromRegistration(Registration registration) throws EntityExistsException;
}
