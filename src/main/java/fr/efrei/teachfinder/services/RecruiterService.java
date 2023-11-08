package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IRecruiterDAO;
import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class RecruiterService {
    @Inject
    IRecruiterDAO recruiterDAO;

    public Recruiter getRecruiter(int recruiterId) throws EntityNotFoundException {
        return recruiterDAO.findById(recruiterId);
    }
}
