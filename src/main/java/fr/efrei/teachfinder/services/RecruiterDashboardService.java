package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.dao.RecruiterDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Stateless
public class RecruiterDashboardService {

    @Inject NeedDAO needDAO;
    @Inject RecruiterDAO recruiterDAO;
    @Inject CandidatureDAO candidatureDAO;

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
