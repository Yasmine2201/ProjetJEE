package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IRecruiterDashboardService {

    List<Need> getRunningNeed(int recruiterId) throws EntityNotFoundException;

    List<Candidature> getCandidatures(int recruiterId) throws EntityNotFoundException;
}
