package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class CandidatureService {

    @Inject private CandidatureDAO candidatureDAO;

    public Candidature getCandidature(CandidatureId candidatureId) throws EntityNotFoundException {
        return candidatureDAO.findById(candidatureId);
    }

    public Candidature refuse(CandidatureId candidatureId) throws EntityNotFoundException {
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setStatus(StatusType.Refused);
        return candidatureDAO.update(candidature);
    }

    public Candidature accept(CandidatureId candidatureId) throws EntityNotFoundException {
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setStatus(StatusType.Accepted);
        return candidatureDAO.update(candidature);
    }

    public Candidature validateFromTeacher(CandidatureId candidatureId) throws EntityNotFoundException {
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setIsValidatedByTeacher(true);
        candidatureDAO.update(candidature);
        return accpetIfBothValidation(candidature);
    }

    public Candidature validateFromRecruiter(CandidatureId candidatureId) throws EntityNotFoundException {
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setIsValidatedByRecruiter(true);
        candidatureDAO.update(candidature);
        return accpetIfBothValidation(candidature);
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
