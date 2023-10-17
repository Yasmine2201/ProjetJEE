package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Disponibility;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IDisponibilityDAO {
    Disponibility create(Disponibility disponibilities)throws EntityExistsException;
    List<Disponibility> findAllByTeacher(int teacherId );

    void delete(Disponibility disponibilities) throws EntityNotFoundException;


}
