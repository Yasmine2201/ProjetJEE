package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.Registration;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

public interface IUserDAO {

    /**
     * Find the ApplicationUser with the unique id
     *
     * @param userId Unique id of the user
     * @return The ApplicationUser or null
     */
    ApplicationUser findById(int userId);

    /**
     * Find the ApplicationUser with the unique login
     *
     * @param login Unique login of the user
     * @return The ApplicationUser or null
     */
    ApplicationUser findByLogin(String login);

    /**
     * Create an ApplicationUser from a validated registration.
     *
     * @param registration Registration validated by an administrator
     * @throws EntityExistsException if ApplicationUser with same login as registration already exists.
     * @return The created ApplicationUser if successful
     */
    ApplicationUser create(@NotNull Registration registration) throws EntityExistsException;

    /**
     * Change the password of an ApplicationUser.
     *
     * @param userId Unique identifier of the user whose password is changed
     * @param newPassword Hash of the new password
     * @throws EntityNotFoundException If ApplicationUser with id userId does not exist.
     */
    void changePassword(int userId, String newPassword) throws EntityNotFoundException;
}
