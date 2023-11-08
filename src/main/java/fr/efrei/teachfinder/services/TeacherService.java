package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IDisponibilityDAO;
import fr.efrei.teachfinder.dao.IEvaluationDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.Teacher;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TeacherService {
    @Inject
    ITeacherDAO teacherDAO;
    @Inject
    IDisponibilityDAO disponibilityDAO;
    @Inject
    IEvaluationDAO evaluationDAO;

    public Teacher getTeacher(int teacherId)  throws EntityNotFoundException {
        Teacher teacher = teacherDAO.findById(teacherId);
        if (teacher == null) throw new EntityNotFoundException("Teacher with id " + teacherId + " not found.");
        return teacher;
    }

    public Teacher updateTeacher(Teacher teacher)  throws EntityNotFoundException {
        return teacherDAO.update(teacher);
    }

    public List<Disponibility> getTeacherFutureDisponibilities(int teacherId) throws EntityNotFoundException {
        if (teacherNotExists(teacherId)) {
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

    public List<Evaluation> getTeacherEvaluations(int teacherId) throws EntityNotFoundException {
        if (teacherNotExists(teacherId)) {
            throw new EntityNotFoundException("No teacher found with id " + teacherId);
        }
        else{
            return evaluationDAO.findAllByTeacher(teacherId);
        }
    }

    boolean teacherNotExists(int teacherId){ return teacherDAO.findById(teacherId) == null;}
}
