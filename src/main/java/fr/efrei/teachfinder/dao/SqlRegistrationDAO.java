package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Registration;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.*;

@Stateless
public class SqlRegistrationDAO implements IRegistrationDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Registration findByLogin(String login) {
        TypedQuery<Registration> query = entityManager
                .createQuery(REGISTRATION_FINDBYLOGIN, Registration.class)
                .setParameter("login", login);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Registration create(@NotNull Registration registration) throws EntityExistsException {
        if (findByLogin(registration.getLogin()) != null)
            throw new EntityExistsException("Registration already exists for this login.");

        entityManager.getTransaction().begin();
        Registration createdRegistration = entityManager.merge(registration);
        entityManager.getTransaction().commit();

        return createdRegistration;
    }

    @Override
    public List<Registration> getAllWithStatus(Registration.Status status) {
        TypedQuery<Registration> query = entityManager
                .createQuery(REGISTRATION_GETALLWITHSTATUS, Registration.class)
                .setParameter("status", status);

        return query.getResultList();
    }

    @Override
    public void changeStatus(String login, Registration.Status status) throws EntityNotFoundException {
        Registration registrationToUpdate = findByLogin(login);
        if (registrationToUpdate == null) throw new EntityNotFoundException("No Registration found with login " + login);
        registrationToUpdate.setStatus(status);

        entityManager.getTransaction().begin();
        entityManager.merge(registrationToUpdate);
        entityManager.getTransaction().commit();
    }
}
