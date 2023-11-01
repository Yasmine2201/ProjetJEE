package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;

public interface ICandidatureService {

    Candidature getCandidature(CandidatureId candidatureId);

    Candidature refuse(CandidatureId candidatureId);

    Candidature accept(CandidatureId candidatureId);

    Candidature validateFromTeacher(CandidatureId candidatureId);

    Candidature validateFromRecruiter(CandidatureId candidatureId);
}
