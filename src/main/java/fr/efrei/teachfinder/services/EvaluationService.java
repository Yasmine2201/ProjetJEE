package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.EvaluationDAO;
import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;
import jakarta.inject.Inject;

public class EvaluationService implements IEvaluationService{

    @Inject
    EvaluationDAO evaluationDAO;
    @Override
    public Evaluation getEvaluation(EvaluationId evaluationId) {
        return evaluationDAO.findById(evaluationId);
    }

    @Override
    public Evaluation upsertEvaluation(Evaluation evaluation) {
        if(evaluationDAO.findById(evaluation.getId())!=null){
            return evaluationDAO.update(evaluation);
        }
        else {
            return evaluationDAO.create(evaluation);
        }

    }
}
