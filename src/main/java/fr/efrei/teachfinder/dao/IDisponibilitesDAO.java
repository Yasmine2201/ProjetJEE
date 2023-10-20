package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Disponibilities;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IDisponibilitesDAO {
    Disponibilities create(Disponibilities disponibilities)throws EntityExistsException;
    List<Disponibilities> findAllByTeacher(int teacherId );

    void delete(Disponibilities disponibilities) throws EntityNotFoundException;


}
