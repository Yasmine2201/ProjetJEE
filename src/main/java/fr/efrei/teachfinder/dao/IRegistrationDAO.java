package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Stateless
public interface IRegistrationDAO {

    /**
     * Find the Registration with the unique id.
     *
     * @param registrationId Unique id of the Registration record to find
     * @return Registration with unique id or null
     */
    Registration findById(int registrationId);

    /**
     * Find a Registration with the login.
     *
     * @param login login of the Registration record to find
     * @return Registration with login or null
     */
    Registration findByLogin(String login);

    /**
     * Create a Registration.
     *
     * @param registration Registration to create
     * @throws EntityExistsException If registration for the same login already exists
     * @return The created Registration
     */
    Registration create(@NotNull Registration registration) throws EntityExistsException;

    /**
     * Get all the registration with the given status.
     *
     * @param status Status filter
     * @return List of the Registrations with the specified status
     */
    List<Registration> getAllWithStatus(StatusType status);

    /**
     * Change the status of a specific Registration (with specified login).
     *
     * @param login Unique login of the Registration to change
     * @param status New status
     * @throws EntityNotFoundException If no Registration with the specified login exists
     */
    void changeStatus(String login, StatusType status) throws EntityNotFoundException;
}
