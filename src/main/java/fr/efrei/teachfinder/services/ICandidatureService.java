package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import jakarta.persistence.EntityNotFoundException;

public interface ICandidatureService {

    Candidature getCandidature(CandidatureId candidatureId) throws EntityNotFoundException;

    Candidature refuse(CandidatureId candidatureId) throws EntityNotFoundException ;

    Candidature accept(CandidatureId candidatureId) throws EntityNotFoundException;

    Candidature validateFromTeacher(CandidatureId candidatureId) throws EntityNotFoundException;

    Candidature validateFromRecruiter(CandidatureId candidatureId) throws EntityNotFoundException;
}
