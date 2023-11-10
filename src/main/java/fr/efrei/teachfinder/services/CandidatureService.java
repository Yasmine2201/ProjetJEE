package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.SessionUser;
import fr.efrei.teachfinder.dao.ICandidatureDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import fr.efrei.teachfinder.entities.StatusType;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class CandidatureService {

    @Inject private ICandidatureDAO candidatureDAO;

    public Candidature getCandidature(CandidatureId candidatureId) throws EntityNotFoundException {
        Candidature candidature = candidatureDAO.findById(candidatureId);
        if (candidature == null) throw new EntityNotFoundException("Candidature with id " + candidatureId + " not found");
        return candidature;
    }

    public Candidature refuseByRecruiter(CandidatureId candidatureId, SessionUser user) throws EntityNotFoundException, IllegalAccessException {
        Candidature candidature = getCandidature(candidatureId);
        if (candidature.getNeed().getRecruiter().getId() != user.getUserId()){
            throw new IllegalAccessException();
        }
        else{
            return refuse(candidatureId);
        }
    }
    public Candidature refuseByTeacher(CandidatureId candidatureId, SessionUser user) throws EntityNotFoundException, IllegalAccessException {
        Candidature candidature = getCandidature(candidatureId);
        if (candidature.getTeacher().getId() != user.getUserId()){
            throw new IllegalAccessException();
        }
        else{
            return refuse(candidatureId);
        }
    }

    public Candidature refuse (CandidatureId candidatureId) throws EntityNotFoundException {
        Candidature candidature = getCandidature(candidatureId);
        candidature.setStatus(StatusType.Refused);
        return candidatureDAO.update(candidature);
    }
    public Candidature acceptByRecruiter(CandidatureId candidatureId, SessionUser user) throws EntityNotFoundException, IllegalAccessException {
        Candidature candidature = getCandidature(candidatureId);
        if (candidature.getNeed().getRecruiter().getId() != user.getUserId()){
            throw new IllegalAccessException();
        }
        else{
            candidature.setIsValidatedByRecruiter(true);
            candidatureDAO.update(candidature);
            return accpetIfBothValidation(candidature);
        }
    }
    public Candidature acceptByTeacher(CandidatureId candidatureId, SessionUser user) throws EntityNotFoundException, IllegalAccessException {
        Candidature candidature = getCandidature(candidatureId);
        if (candidature.getTeacher().getId() != user.getUserId()){
            throw new IllegalAccessException();
        }
        else{
            candidature.setIsValidatedByTeacher(true);
            candidatureDAO.update(candidature);
            return accpetIfBothValidation(candidature);
        }
    }


    public Candidature accept(CandidatureId candidatureId) throws EntityNotFoundException {
        Candidature candidature = getCandidature(candidatureId);
        candidature.setStatus(StatusType.Accepted);
        return candidatureDAO.update(candidature);
    }

    public Candidature accpetIfBothValidation(Candidature candidature) throws EntityNotFoundException {
        if (candidature.getIsValidatedByRecruiter() && candidature.getIsValidatedByTeacher()) {
            return accept(candidature.getId());
        }
        return candidature;
    }

    boolean candidatureNotExists(CandidatureId candidatureId){
        return candidatureDAO.findById(candidatureId) == null;
    }
}
