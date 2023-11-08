package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.utils.Constants;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;

public class RecruiterDAO implements IRecruiterDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Recruiter findById(int recruiterId) {
        TypedQuery<Recruiter> query = entityManager
                .createQuery(Constants.QueryRequests.RECRUITER_FINDBYID, Recruiter.class)
                .setParameter("recruiterId", recruiterId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Recruiter create(Recruiter recruiter) throws EntityExistsException {
        if (findById(recruiter.getId()) != null) throw new EntityExistsException("Recruiter already exists");

        entityManager.getTransaction().begin();
        Recruiter createdRecruiter = entityManager.merge(recruiter);
        entityManager.getTransaction().commit();

        return createdRecruiter;
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

    @Override
    public List<Recruiter> findAllBySchool(String schoolName) {
        TypedQuery<Recruiter> query = entityManager.createQuery(
                        Constants.QueryRequests.RECRUITER_FINDALL_BY_SCHOOL, Recruiter.class)
                .setParameter("schoolName", schoolName);

        return query.getResultList();
    }
}