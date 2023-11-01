package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.QueryRequests;


public class RegistrationDAO implements IRegistrationDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Registration findById(int registrationId) {
        TypedQuery<Registration> query = entityManager
                .createQuery(QueryRequests.REGISTRATION_FINDBYID, Registration.class)
                .setParameter("registrationId", registrationId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Registration findByLogin(String login) {
        TypedQuery<Registration> query = entityManager
                .createQuery(QueryRequests.REGISTRATION_FINDBYLOGIN, Registration.class)
                .setParameter("login", login);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Registration create(@NotNull Registration registration) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            Registration createdRegistration = entityManager.merge(registration);
            entityManager.getTransaction().commit();

            return createdRegistration;
        } catch (EntityExistsException ex) {
            throw new EntityExistsException("Registration already exists for this login.");
        }
    }

    @Override
    public List<Registration> getAllWithStatus(StatusType status) {
        TypedQuery<Registration> query = entityManager
                .createQuery(QueryRequests.REGISTRATION_GETALLWITHSTATUS, Registration.class)
                .setParameter("status", status);

        return query.getResultList();
    }

    @Override
    public void changeStatus(String login, StatusType status) throws EntityNotFoundException {
        Registration registrationToUpdate = findByLogin(login);
        if (registrationToUpdate == null) throw new EntityNotFoundException("No Registration found with login " + login);
        registrationToUpdate.setStatus(status);

        entityManager.getTransaction().begin();
        entityManager.merge(registrationToUpdate);
        entityManager.getTransaction().commit();
    }
}
