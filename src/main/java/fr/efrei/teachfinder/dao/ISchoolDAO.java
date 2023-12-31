package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;

import java.util.List;


public interface ISchoolDAO {

    School findByName(String schoolName);

    School create(School school ) throws EntityExistsException;

    List<School> getAll();

    List<Recruiter> findAllRecruiters(String schoolName);

    /**
     * Update a School.
     *
     * @param school School entity containing name and fields to update
     * @throws EntityNotFoundException If School with schoolName does not exist.
     */
    void update(School school) throws EntityNotFoundException;

    List<School> searchWithString(String research);
}
