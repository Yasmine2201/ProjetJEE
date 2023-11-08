package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Teacher;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;

import java.util.List;


public interface ITeacherDAO {

    Teacher findById(int teacherId);

    Teacher create(Teacher teacher) throws EntityExistsException;

    Teacher update(Teacher teacher) throws EntityNotFoundException;

    List<Teacher> getAll ();

    List<Teacher> searchWithSkills(String research);
}
