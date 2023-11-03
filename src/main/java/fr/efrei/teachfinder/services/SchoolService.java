package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.INeedDAO;
import fr.efrei.teachfinder.dao.IRecruiterDAO;
import fr.efrei.teachfinder.dao.ISchoolDAO;
import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class SchoolService implements ISchoolService {

    @Inject
    private ISchoolDAO schoolDAO;

    @Inject
    private INeedDAO needDAO;

    @Inject
    private IRecruiterDAO recruiterDAO;

    @Override
    public School createSchool(School school) throws EntityExistsException, IncompleteEntityException {
        String schoolName = school.getSchoolName();
        if (StringUtils.isNullOrEmpty(schoolName)) {
            throw new IncompleteEntityException();
        }

        return schoolDAO.create(school);
    }

    @Override
    public School getSchool(String schoolName) throws EntityNotFoundException {
        return schoolDAO.findByName(schoolName);
    }

    @Override
    public List<School> getAllSchools() {
        return schoolDAO.getAll();
    }

    @Override
    public List<Need> getSchoolRunningNeeds(String schoolName) {

        return null;
    }

    @Override
    public List<Recruiter> getSchoolRecruiters(String schoolName) {
        return null;
    }

    @Override
    public School updateSchool(School school) {
        return null;
    }
}
