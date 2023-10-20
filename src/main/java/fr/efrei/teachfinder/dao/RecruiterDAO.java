package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class RecruiterDAO implements IRecruiterDAO{
    @Override
    public Recruiter findById(int recruiterId) {
        return null;
    }

    @Override
    public Recruiter create(Recruiter recruiter) throws EntityExistsException {
        return null;
    }

    @Override
    public Recruiter update(Recruiter recruiter) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<Recruiter> findAllBySchool(int schoolId) {
        return null;
    }
}
