package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.entities.Teacher;

import java.util.List;

public interface ITeacherService {

    Teacher getTeacher(int teacherId);

    Teacher updateTeacher(Teacher teacher);

    List<Disponibility> getTeacherFutureDisponibilities(int teacherId);

    List<Disponibility> getTeacherEvaluations(int teacherId);
}
