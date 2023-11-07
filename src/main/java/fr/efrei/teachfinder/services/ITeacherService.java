package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.entities.Evaluation;
import fr.efrei.teachfinder.entities.Teacher;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ITeacherService {

    Teacher getTeacher(int teacherId)  throws EntityNotFoundException;

    Teacher updateTeacher(Teacher teacher)  throws EntityNotFoundException;

    List<Disponibility> getTeacherFutureDisponibilities(int teacherId)  throws EntityNotFoundException;

    List<Evaluation> getTeacherEvaluations(int teacherId) throws EntityNotFoundException;
}
