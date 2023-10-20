package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.CandidatureId;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CandidatureDAO implements ICandidatureDAO{
    @Override
    public Candidature findById(CandidatureId candidatureId) {
        return null;
    }

    @Override
    public Candidature create(Candidature candidature) throws EntityExistsException {
        return null;
    }

    @Override
    public Candidature update(Candidature candidature) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<Candidature> findAllByTeacher(int teacherId) {
        return null;
    }

    @Override
    public List<Candidature> findAllByRecruiter(int recruiterId) {
        return null;
    }

    @Override
    public List<Candidature> findAllByNeed(int needId) {
        return null;
    }
}
