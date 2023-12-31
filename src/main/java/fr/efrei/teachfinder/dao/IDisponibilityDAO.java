package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;

import java.util.List;

public interface IDisponibilityDAO {

    Disponibility findById(int disponibilityId);

    Disponibility create(Disponibility disponibilities) throws EntityExistsException;

    List<Disponibility> findAllByTeacher(int teacherId);

    Disponibility update(Disponibility disponibility) throws EntityNotFoundException;

    void delete(Disponibility disponibilities) throws EntityNotFoundException;
}
