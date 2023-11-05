package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.dao.RecruiterDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RecruiterDashboardService implements IRecruiterDashboardService{

    @Inject
    NeedDAO needDAO;
    @Inject
    RecruiterDAO recruiterDAO;

    @Inject
    CandidatureDAO candidatureDAO;

    @Override
    public List<Need> getRunningNeed(int recruiterId) {
        if (!recruiterExists(recruiterId)) {
            throw new EntityNotFoundException("No recruiter found with id " + recruiterId);
        }
        else{ return needDAO.findAllByRecruiter(recruiterId);}
    }




    @Override
    public List<Candidature> getCandidatures(int recruiterId) {
        List<Need> runningNeeds=getRunningNeed(recruiterId);
        List<Candidature> candidaturesList=new ArrayList<>();
        for (Need need : runningNeeds){
            List<Candidature> candidaturesForNeed = candidatureDAO.findAllByNeed(need.getId());
            candidaturesList.addAll(candidaturesForNeed);
        }
        return candidaturesList;
    }
    private boolean recruiterExists(int recruiterId) { return recruiterDAO.findById(recruiterId)!=  null ; }

}
