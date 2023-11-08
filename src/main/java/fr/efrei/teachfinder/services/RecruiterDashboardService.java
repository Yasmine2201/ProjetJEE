package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.ICandidatureDAO;
import fr.efrei.teachfinder.dao.INeedDAO;
import fr.efrei.teachfinder.dao.IRecruiterDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.StatusType;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class RecruiterDashboardService {

    @Inject
    INeedDAO needDAO;
    @Inject
    IRecruiterDAO recruiterDAO;
    @Inject ICandidatureDAO candidatureDAO;

    public List<Need> getRunningNeed(int recruiterId) throws EntityNotFoundException {

        if (!recruiterExists(recruiterId))
            throw new EntityNotFoundException("No recruiter found with id " + recruiterId);

        List<Need> needs = needDAO.findAllByRecruiter(recruiterId);

        return needs.stream()
                .filter(
                        need -> need.getCandidatures()
                                .stream()
                                .noneMatch(candidature -> candidature.getStatus() == StatusType.Accepted))
                .toList();
    }

    public List<Candidature> getCandidatures(int recruiterId) throws EntityNotFoundException {
        // Only pending candidatures
        List<Candidature> candidaturesList = candidatureDAO.findAllByRecruiter(recruiterId);
        return candidaturesList.stream()
                .filter(candidature -> candidature.getStatus() == StatusType.Pending)
                .toList();
    }

    private boolean recruiterExists(int recruiterId) {
        return recruiterDAO.findById(recruiterId) != null;
    }
}
