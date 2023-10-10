package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Registration;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IRegistrationDAO {

    /**
     * Find the Registration with the unique login.
     *
     * @param login Unique login of the Registration record to find
     * @return Registration with unique login or null
     */
    Registration findByLogin(String login);

    /**
     * Create a Registration.
     *
     * @param registration Registration to create
     * @throws EntityExistsException If registration for the same login already exists
     * @return The created Registration
     */
    Registration create(Registration registration) throws EntityExistsException;

    /**
     * Get all the registration with the given status.
     *
     * @param status Status filter
     * @return List of the Registrations with the specified status
     */
    List<Registration> getAllWithStatus(Registration.Status status);

    /**
     * Change the status of a specific Registration (with specified login).
     *
     * @param login Unique login of the Registration to change
     * @param status New status
     * @throws EntityNotFoundException If no Registration with the specified login exists
     */
    void changeStatus(String login, Registration.Status status) throws EntityNotFoundException;
}
