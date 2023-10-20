package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.School;

import java.util.List;
import jakarta.persistence.EntityExistsException;


public interface ISchoolDAO {
    School findById(int schoolId);
    School create(School school )throws EntityExistsException;

    List<School> getAll();


}
