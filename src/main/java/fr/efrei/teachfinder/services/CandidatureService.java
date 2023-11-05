package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

public class CandidatureService implements ICandidatureService{

    @Inject
    CandidatureDAO candidatureDAO;
    @Override
    public Candidature getCandidature(CandidatureId candidatureId) {
        return candidatureDAO.findById(candidatureId);
    }

    @Override
    public Candidature refuse(CandidatureId candidatureId) {
        if(candidatureExists(candidatureId)){
            throw new EntityNotFoundException("No candidature found with id "+candidatureId);
        }
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setStatus(StatusType.Refused);
        return candidatureDAO.update(candidature);
    }

    @Override
    public Candidature accept(CandidatureId candidatureId) {
        if(candidatureExists(candidatureId)){
            throw new EntityNotFoundException("No candidature found with id "+candidatureId);
        }
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setStatus(StatusType.Accepted);
        return candidatureDAO.update(candidature);
    }

    @Override
    public Candidature validateFromTeacher(CandidatureId candidatureId) {
        if(candidatureExists(candidatureId)){
            throw new EntityNotFoundException("No candidature found with id "+candidatureId);
        }
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setIsValidatedByTeacher(true);
        return candidatureDAO.update(candidature);
    }

    @Override
    public Candidature validateFromRecruiter(CandidatureId candidatureId) {
        if(candidatureExists(candidatureId)){
            throw new EntityNotFoundException("No candidature found with id "+candidatureId);
        }
        Candidature candidature=candidatureDAO.findById(candidatureId);
        candidature.setIsValidatedByRecruiter(true);
        return candidatureDAO.update(candidature);
    }

    boolean candidatureExists(CandidatureId candidatureId){
        return candidatureDAO.findById(candidatureId) == null;
    }
}
