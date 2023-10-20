package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.EvaluationId;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class EvaluationDAO implements IEvaluationDAO{
    @Override
    public Evaluation findById(EvaluationId evaluationId) {
        return null;
    }

    @Override
    public Evaluation create(Evaluation ev) throws EntityExistsException {
        return null;
    }

    @Override
    public Evaluation update(Evaluation ev) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<Evaluation> findAllByTeacher(int teacherId) {
        return null;
    }
}
