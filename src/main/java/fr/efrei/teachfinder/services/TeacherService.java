package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.DisponibilityDAO;
import fr.efrei.teachfinder.dao.EvaluationDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.Teacher;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class TeacherService implements ITeacherService{
    @Inject
    TeacherDAO teacherDAO;

    @Inject
    DisponibilityDAO disponibilityDAO;
    @Inject
    EvaluationDAO evaluationDAO;

    @Override
    public Teacher getTeacher(int teacherId) {
        return teacherDAO.findById(teacherId);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherDAO.update(teacher);
    }

    @Override
    public List<Disponibility> getTeacherFutureDisponibilities(int teacherId) {
        if (teacherExists(teacherId)) {
            throw new EntityNotFoundException("No teacher found with id " + teacherId);
        }
        else {
            return disponibilityDAO.findAllByTeacher(teacherId);
        }
    }


    @Override
    public List<Evaluation> getTeacherEvaluations(int teacherId) {
        if (teacherExists(teacherId)) {
            throw new EntityNotFoundException("No teacher found with id " + teacherId);
        }
        else{
            return evaluationDAO.findAllByTeacher(teacherId);
        }
    }

    boolean teacherExists(int teacherId){ return teacherDAO.findById(teacherId) == null;}

}
