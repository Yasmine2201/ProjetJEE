package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.QueryRequests;

public class CandidatureDAO implements ICandidatureDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Candidature findById(CandidatureId candidatureId) {
        TypedQuery<Candidature> query = entityManager
            .createQuery(QueryRequests.CANDIDATURE_FINDBYID, Candidature.class)
            .setParameter("teacherId", candidatureId.getTeacherId())
            .setParameter("needId", candidatureId.getNeedId());

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Candidature create(Candidature candidature) throws EntityExistsException {
        if (findById(candidature.getId()) != null) throw new EntityExistsException("Candidature already exists");

        entityManager.getTransaction().begin();
        Candidature createdCandidature = entityManager.merge(candidature);
        entityManager.getTransaction().commit();

        return createdCandidature;
    }

    @Override
    public Candidature update(Candidature candidature) throws EntityNotFoundException {
        Candidature existingCandidature = findById(candidature.getId());
        if (existingCandidature == null) {
            throw new EntityNotFoundException("Candidature with ID " + candidature.getId() + " not found");
        }
        entityManager.getTransaction().begin();
        Candidature updatedCandidature = entityManager.merge(candidature);
        entityManager.getTransaction().commit();
        return updatedCandidature;

    }

    @Override
    public List<Candidature> findAllByTeacher(int teacherId) {
        TypedQuery<Candidature> query = entityManager.createQuery(
                        QueryRequests.CANDIDATURE_FINDALL_BY_TEACHER, Candidature.class)
                .setParameter("teacherId", teacherId);

        return query.getResultList();
    }

    @Override
    public List<Candidature> findAllByRecruiter(int recruiterId) {
        TypedQuery<Candidature> query = entityManager.createQuery(
                        QueryRequests.CANDIDATURE_FINDALL_BY_RECRUITER, Candidature.class)
                .setParameter("recruiterId", recruiterId);

        return query.getResultList();
    }

    @Override
    public List<Candidature> findAllByNeed(int needId) {
        TypedQuery<Candidature> query = entityManager.createQuery(
                        QueryRequests.CANDIDATURE_FINDALL_BY_NEED, Candidature.class)
                .setParameter("needId", needId);

        return query.getResultList();
    }
}
