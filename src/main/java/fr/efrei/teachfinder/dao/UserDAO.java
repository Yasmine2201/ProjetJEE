package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.QueryRequests;

public class UserDAO implements IUserDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public ApplicationUser findById(int userId) {
        TypedQuery<ApplicationUser> query = entityManager
                .createQuery(QueryRequests.APPLICATIONUSER_FINDBYID, ApplicationUser.class)
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
                .createQuery(QueryRequests.APPLICATIONUSER_FINDBYLOGIN, ApplicationUser.class)
                .setParameter("login", login);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public ApplicationUser create(@NotNull ApplicationUser user) throws EntityExistsException {
        if (findByLogin(user.getLogin()) != null) {
            throw new EntityExistsException("ApplicationUser with login " + user.getLogin() + " already exists.");
        }

        entityManager.getTransaction().begin();
        ApplicationUser userCreated = entityManager.merge(user);
        entityManager.getTransaction().commit();

        return userCreated;
    }

    @Override
    public void update(ApplicationUser user) throws EntityNotFoundException {
        if (findById(user.getId()) == null) throw new EntityNotFoundException("No ApplicationUser found with id " + user.getId());

        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }
}
