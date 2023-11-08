package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;

import java.util.List;

public interface IRecruiterDAO {

    Recruiter findById(int recruiterId);

    Recruiter create(Recruiter recruiter) throws EntityExistsException;

    Recruiter update(Recruiter recruiter) throws EntityNotFoundException;

    List<Recruiter> findAllBySchool(String schoolName);
}
