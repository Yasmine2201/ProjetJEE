package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.DisponibilityDAO;
import fr.efrei.teachfinder.dao.EvaluationDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.Teacher;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
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

        List<Disponibility> disponibilities = disponibilityDAO.findAllByTeacher(teacherId);
        LocalDateTime todayDate = LocalDateTime.now();


        List<Disponibility> filteredDisponibilities = new ArrayList<>();


        for (Disponibility disponibility : disponibilities) {
            if (disponibility.getEndDate().isAfter(todayDate)) {
                filteredDisponibilities.add(disponibility);
            }
        }
        return filteredDisponibilities;
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

    boolean teacherExists(int teacherId){ return teacherDAO.findById(teacherId) != null;}

}