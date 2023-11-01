package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.Registration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import static fr.efrei.teachfinder.utils.Constants.*;

public class UserDAO implements IUserDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public ApplicationUser findById(int userId) {
        TypedQuery<ApplicationUser> query = entityManager
                .createQuery(APPLICATIONUSER_FINDBYID, ApplicationUser.class)
                .setParameter("id", userId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public ApplicationUser findByLogin(String login) {
        TypedQuery<ApplicationUser> query = entityManager
                .createQuery(APPLICATIONUSER_FINDBYLOGIN, ApplicationUser.class)
                .setParameter("login", login);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public ApplicationUser create(@NotNull Registration registration) throws EntityExistsException {
        if (findByLogin(registration.getLogin()) != null) {
            throw new EntityExistsException("ApplicationUser with login " + registration.getLogin() + " already exists.");
        }

        ApplicationUser user = new ApplicationUser();
        user.setLogin(registration.getLogin());
        user.setPassword(registration.getPassword());
        user.setFirstname(registration.getFirstname());
        user.setLastname(registration.getLastname());
        user.setRole(registration.getRole());
        user.setEmail(registration.getEmail());
        user.setPhone(registration.getPhone());

        entityManager.getTransaction().begin();
        ApplicationUser userCreated = entityManager.merge(user);
        entityManager.getTransaction().commit();

        return userCreated;
    }

    @Override
    public void changePassword(int userId, String newPassword) throws EntityNotFoundException {
        ApplicationUser userToUpdate = findById(userId);
        if (userToUpdate == null) throw new EntityNotFoundException("No ApplicationUser found with id " + userId);
        userToUpdate.setPassword(newPassword);

        entityManager.getTransaction().begin();
        entityManager.merge(userToUpdate);
        entityManager.getTransaction().commit();
    }
}
