package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.INeedDAO;
import fr.efrei.teachfinder.dao.IRecruiterDAO;
import fr.efrei.teachfinder.dao.ISchoolDAO;
import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.entities.StatusType;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Stateless
public class SchoolService implements ISchoolService {

    @Inject
    private ISchoolDAO schoolDAO;

    @Inject
    private INeedDAO needDAO;

    @Inject
    private IRecruiterDAO recruiterDAO;

    @Override
    public School createSchool(School school) throws EntityExistsException, IncompleteEntityException {
        if (isSchoolIncomplete(school)) throw new IncompleteEntityException("Missing field of school");
        try {
            return schoolDAO.create(school);
        } catch (EntityExistsException e) {
            // Intermediary catch to avoid EJBException
            throw new EntityExistsException(e.getMessage());
        }
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
    public List<Need> getSchoolRunningNeeds(String schoolName) throws EntityNotFoundException {
        if (!schoolExists(schoolName)) {
            throw new EntityNotFoundException("No school found with name " + schoolName);
        }

        List<Need> schoolNeeds = needDAO.findAllBySchool(schoolName);

        return schoolNeeds.stream()
                .filter(
                        need -> need.getCandidatures()
                                .stream()
                                .noneMatch(candidature -> candidature.getStatus() == StatusType.Accepted))
                .toList();
    }

    @Override
    public List<Recruiter> getSchoolRecruiters(String schoolName) throws EntityNotFoundException {
        if (!schoolExists(schoolName)) {
            throw new EntityNotFoundException("No school found with name " + schoolName);
        }

        return recruiterDAO.findAllBySchool(schoolName);
    }

    @Override
    public void updateSchool(School school) throws EntityNotFoundException, IncompleteEntityException {
        if (isSchoolIncomplete(school)) throw new IncompleteEntityException("Missing field of school");
        schoolDAO.update(school);
    }

    @Override
    public boolean schoolExists(String schoolName) {
        return schoolDAO.findByName(schoolName) != null;
    }

    public boolean isSchoolIncomplete(School school) {
        String schoolName = school.getSchoolName();
        String address = school.getAddress();
        String specializations = school.getSpecializations();
        return StringUtils.isNullOrEmpty(schoolName)
            || StringUtils.isNullOrEmpty(address)
            || StringUtils.isNullOrEmpty(specializations);
    }
}
