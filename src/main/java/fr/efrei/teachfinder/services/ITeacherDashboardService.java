package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;

import java.util.List;

public interface ITeacherDashboardService {

    List<Need> getInterestingNeeds(int teacherId);

    List<Candidature> getCandidatures(int teacherId);
}
