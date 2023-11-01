package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;

import java.util.List;

public interface IRecruiterDashboardService {

    List<Need> getRunningNeed(int recruiterId);

    List<Candidature> getCandidatures(int recruiterId);
}
