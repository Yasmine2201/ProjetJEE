package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import jakarta.persistence.*;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.RECRUITER_FINDBYID;

public class RecruiterDAO implements IRecruiterDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Recruiter findById(int recruiterId) {
        TypedQuery<Recruiter> query = entityManager
                .createQuery(RECRUITER_FINDBYID, Recruiter.class)
                .setParameter("recruiterId", recruiterId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Recruiter create(Recruiter recruiter) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            Recruiter createdRecruiter = entityManager.merge(recruiter);
            entityManager.getTransaction().commit();

            return createdRecruiter;
        } catch (EntityExistsException ex) {
            throw new EntityExistsException("Recruiter already exists");
        }

    }

    @Override
    public Recruiter update(Recruiter recruiter) throws EntityNotFoundException {
            Recruiter existingRecruiter = findById(recruiter.getId());

            if (existingRecruiter == null) {
                throw new EntityNotFoundException("Recruiter with ID " + recruiter.getId() + " not found");
            }
            entityManager.getTransaction().begin();
            Recruiter updatedRecruiter = entityManager.merge(existingRecruiter);
            entityManager.getTransaction().commit();
            return updatedRecruiter;

    }
}