package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.StatusType;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
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
    public List<Registration> findAllWithLogin(String login) {
        TypedQuery<Registration> query = entityManager
                .createQuery(QueryRequests.REGISTRATION_FINDBYLOGIN, Registration.class)
                .setParameter("login", login);

        return query.getResultList();
    }

    @Override
    public Registration create(@NotNull Registration registration) throws EntityExistsException {
        if (findById(registration.getRegistrationId()) != null) throw new EntityExistsException("Registration already exists for this login.");

        entityManager.getTransaction().begin();
        Registration createdRegistration = entityManager.merge(registration);
        entityManager.getTransaction().commit();

        return createdRegistration;
    }

    @Override
    public List<Registration> getAllWithStatus(StatusType status) {
        TypedQuery<Registration> query = entityManager
                .createQuery(QueryRequests.REGISTRATION_GETALLWITHSTATUS, Registration.class)
                .setParameter("status", status);

        return query.getResultList();
    }

    @Override
    public void changeStatus(int registrationId, StatusType status) throws EntityNotFoundException {
        Registration registrationToUpdate = findById(registrationId);
        if (registrationToUpdate == null) throw new EntityNotFoundException("No Registration found with id " + registrationId);
        registrationToUpdate.setStatus(status);

        entityManager.getTransaction().begin();
        entityManager.merge(registrationToUpdate);
        entityManager.getTransaction().commit();
    }
}
