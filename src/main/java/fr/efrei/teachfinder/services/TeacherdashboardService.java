package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class TeacherdashboardService implements  ITeacherDashboardService{

    @Inject
    NeedDAO needDAO;
    @Inject
    CandidatureDAO candidatureDAO;


    @Override
    public List<Need> getInterestingNeeds(int teacherId) {
        //à modifier encore pour prendre en compte les preferences du teacher
        List<Need> needs = needDAO.getAll();


        return needs.stream()
                .filter(
                        need -> need.getCandidatures()
                                .stream()
                                .noneMatch(candidature -> candidature.getStatus() == StatusType.Accepted))
                .toList();

    }

    @Override
    public List<Candidature> getCandidatures(int teacherId) {
        return candidatureDAO.findAllByTeacher(teacherId);
    }
}
