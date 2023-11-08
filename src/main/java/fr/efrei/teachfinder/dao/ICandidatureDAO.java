package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;

import java.util.List;

public interface ICandidatureDAO {

    Candidature findById(CandidatureId candidatureId);

    Candidature create(Candidature candidature) throws EntityExistsException;

    Candidature update(Candidature candidature) throws EntityNotFoundException;

    List<Candidature> findAllByTeacher(int teacherId);

    List<Candidature> findAllByRecruiter(int recruiterId);

    List<Candidature> findAllByNeed(int needId);
}
