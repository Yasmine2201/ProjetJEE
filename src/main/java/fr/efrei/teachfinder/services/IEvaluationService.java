package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;
import jakarta.persistence.EntityNotFoundException;

public interface IEvaluationService {

    Evaluation getEvaluation(EvaluationId evaluationId) throws EntityNotFoundException;

    Evaluation upsertEvaluation(Evaluation evaluation);
}
