package fr.efrei.teachfinder.dao;


import fr.efrei.teachfinder.entities.Experience;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IExperienceDAO {

    Experience create(Experience disponibilities)throws EntityExistsException;
    List<Experience> findAllByTeacher(int teacherId );

    List<Experience> findAllBySchool(int schoolId );


    void delete(Experience disponibilities) throws EntityNotFoundException;
}
