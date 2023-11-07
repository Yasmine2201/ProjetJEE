package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Recruiter;
import jakarta.persistence.EntityNotFoundException;

public interface IRecruiterService {

    Recruiter getRecruiter(int recruiterId) throws EntityNotFoundException;
}
