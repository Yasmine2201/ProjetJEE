package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.RecruiterDAO;
import fr.efrei.teachfinder.entities.Recruiter;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class RecruiterService implements IRecruiterService{
    @Inject
    RecruiterDAO recruiterDAO;

    @Override
    public Recruiter getRecruiter(int recruiterId) throws EntityNotFoundException {
        return recruiterDAO.findById(recruiterId);
    }
}
