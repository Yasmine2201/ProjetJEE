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
     * Update an ApplicationUser.
     *
     * @param user user containing id and fields to update
     * @throws EntityNotFoundException If ApplicationUser does not exist.
     */
    void update(ApplicationUser user) throws EntityNotFoundException;
}
