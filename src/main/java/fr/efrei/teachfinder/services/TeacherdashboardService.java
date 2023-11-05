package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import jakarta.inject.Inject;

import java.util.List;

public class TeacherdashboardService implements  ITeacherDashboardService{

    @Inject
    NeedDAO needDAO;
    @Inject
    CandidatureDAO candidatureDAO;


    @Override
    public List<Need> getInterestingNeeds(int teacherId) {
        return needDAO.getAll();
    }

    @Override
    public List<Candidature> getCandidatures(int teacherId) {
        return candidatureDAO.findAllByTeacher(teacherId);
    }
}
