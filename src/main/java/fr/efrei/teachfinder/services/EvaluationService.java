package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.EvaluationDAO;
import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class EvaluationService {

    @Inject
    EvaluationDAO evaluationDAO;

    public Evaluation getEvaluation(EvaluationId evaluationId) {
        return evaluationDAO.findById(evaluationId);
    }

    public Evaluation upsertEvaluation(Evaluation evaluation) throws EntityExistsException, EntityNotFoundException {
        return (evaluationDAO.findById(evaluation.getId()) == null
            ? evaluationDAO.create(evaluation)
            : evaluationDAO.update(evaluation));
    }
}
