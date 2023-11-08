package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ITeacherDashboardService {

    List<Need> getInterestingNeeds(int teacherId) throws EntityNotFoundException;

    List<Candidature> getCandidatures(int teacherId) throws EntityNotFoundException;
}
