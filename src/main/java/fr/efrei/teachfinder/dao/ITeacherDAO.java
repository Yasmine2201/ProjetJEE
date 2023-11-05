package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Teacher;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


public interface ITeacherDAO {

    Teacher findById(int teacherId);

    Teacher create(Teacher teacher) throws EntityExistsException;

    Teacher update(Teacher teacher) throws EntityNotFoundException;

    List<Teacher> getAll ();

    List<Teacher> searchWithSkills(String research);
}
