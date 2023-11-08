package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;

import java.util.List;

public interface IEvaluationDAO {

    Evaluation findById(EvaluationId evaluationId);

    Evaluation create(Evaluation ev) throws EntityExistsException;

    Evaluation update(Evaluation ev) throws EntityNotFoundException;

    List<Evaluation> findAllByTeacher(int teacherId);
}