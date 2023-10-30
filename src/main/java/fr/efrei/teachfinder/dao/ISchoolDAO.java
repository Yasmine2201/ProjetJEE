package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;
import jakarta.persistence.EntityExistsException;

import java.util.List;


public interface ISchoolDAO {

    School findByName(String schoolName);

    School create(School school )throws EntityExistsException;

    List<School> getAll();

    List<Recruiter> findAllRecruiters(String schoolName);
}
