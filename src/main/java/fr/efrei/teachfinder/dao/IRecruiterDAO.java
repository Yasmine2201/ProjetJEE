package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

public interface IRecruiterDAO {

    Recruiter findById(int recruiterId);

    Recruiter create(Recruiter recruiter) throws EntityExistsException;

    Recruiter update(Recruiter recruiter) throws EntityNotFoundException;
}
