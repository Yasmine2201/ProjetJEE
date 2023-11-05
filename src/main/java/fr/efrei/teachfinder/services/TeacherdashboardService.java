package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class TeacherdashboardService implements ITeacherDashboardService {

    @Inject
    TeacherDAO teacherDAO;
    @Inject
    NeedDAO needDAO;
    @Inject
    CandidatureDAO candidatureDAO;


    @Override
    public List<Need> getInterestingNeeds(int teacherId) {

        List<Need> needs = needDAO.getAll();
        Teacher teacher = teacherDAO.findById(teacherId);


        List<Need> filtredNeeds = needs.stream()
                .filter(
                        need -> need.getCandidatures()
                                .stream()
                                .noneMatch(candidature -> candidature.getStatus() == StatusType.Accepted))
                .toList();
        //We consider ContractType - schooInterests - personnalInterests
        String teacherPersonnalInterests = teacher.getPersonnalInterests();
        ContractType teacherContractType = teacher.getContractType();
        String teacherSchoolInterests = teacher.getSchoolInterests();

        return filtredNeeds.stream().filter(
                need -> teacherPersonnalInterests.contains(need.getSubject()) && need.getContractType().equals(teacherContractType)
                        && teacherSchoolInterests.contains(need.getSchoolName().getSchoolName())
        ).toList();

    }

    @Override
    public List<Candidature> getCandidatures(int teacherId) {
        return candidatureDAO.findAllByTeacher(teacherId);
    }
}
