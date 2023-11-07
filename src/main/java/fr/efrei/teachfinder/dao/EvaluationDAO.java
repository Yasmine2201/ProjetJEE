package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.QueryRequests;

public class EvaluationDAO implements IEvaluationDAO{

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public Evaluation findById(EvaluationId evaluationId)
    {
        TypedQuery<Evaluation> query = entityManager
            .createQuery(QueryRequests.EVALUATION_FINDBYID, Evaluation.class)
            .setParameter("schoolName", evaluationId.getSchoolName())
            .setParameter("teacherId", evaluationId.getTeacherId());

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Evaluation create(Evaluation ev) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            Evaluation createdEvaluation = entityManager.merge(ev);
            entityManager.getTransaction().commit();

            return createdEvaluation;
        } catch (EntityExistsException ex) {
            throw new EntityExistsException("Evaluation already exists");
        }
    }

    @Override
    public Evaluation update(Evaluation ev) throws EntityNotFoundException {
        Evaluation existingEvaluation = findById(ev.getId());
        if (existingEvaluation == null) {
            throw new EntityNotFoundException("Evaluation with ID " + ev.getId() + " not found");
        }
        entityManager.getTransaction().begin();
        Evaluation updatedEvaluation = entityManager.merge(existingEvaluation);
        entityManager.getTransaction().commit();
        return updatedEvaluation;
    }

    @Override
    public List<Evaluation> findAllByTeacher(int teacherId) {
        TypedQuery<Evaluation> query = entityManager.createQuery(
                        QueryRequests.EVALUATION_FINDALL_BY_TEACHER, Evaluation.class)
                .setParameter("teacherId", teacherId);

        return query.getResultList();
    }
}
