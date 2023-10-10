package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.Registration;
import jakarta.ejb.Stateless;
import jakarta.validation.constraints.NotNull;

@Stateless
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
     * @return The created ApplicationUser if successful
     */
    ApplicationUser create(@NotNull Registration registration);

    /**
     * Change the password of an ApplicationUser.
     *
     * @param userId Unique identifier of the user whose password is changed
     * @param newPassword Hash of the new password
     */
    void changePassword(int userId, String newPassword);
}
