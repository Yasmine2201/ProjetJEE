package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ISchoolService {

    School createSchool(School school) throws EntityExistsException, IncompleteEntityException;

    School getSchool(String schoolName) throws EntityNotFoundException;

    List<School> getAllSchools();

    List<Need> getSchoolRunningNeeds(String schoolName) throws EntityNotFoundException;

    List<Recruiter> getSchoolRecruiters(String schoolName) throws EntityNotFoundException;

    School updateSchool(School school) throws EntityNotFoundException, IncompleteEntityException;
}
