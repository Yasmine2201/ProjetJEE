package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.EvaluationDAO;
import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class EvaluationService {

    @Inject
    EvaluationDAO evaluationDAO;

    public Evaluation getEvaluation(EvaluationId evaluationId)throws EntityNotFoundException {
        return evaluationDAO.findById(evaluationId);
    }

    public Evaluation upsertEvaluation(Evaluation evaluation) throws EntityExistsException {
        if(evaluationDAO.findById(evaluation.getId())!=null){
            return evaluationDAO.update(evaluation);
        }
        else {
            return evaluationDAO.create(evaluation);
        }
    }
}
