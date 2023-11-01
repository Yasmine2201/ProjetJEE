package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;

public interface IEvaluationService {

    Evaluation getEvaluation(EvaluationId evaluationId);

    Evaluation upsertEvaluation(Evaluation evaluation);
}
