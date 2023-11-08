package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;

@Stateless
public class TeacherDashboardService implements ITeacherDashboardService {

    @Inject private TeacherDAO teacherDAO;
    @Inject private NeedDAO needDAO;
    @Inject private CandidatureDAO candidatureDAO;

    @Override
    public List<Need> getInterestingNeeds(int teacherId) throws EntityNotFoundException {
        Teacher teacher = teacherDAO.findById(teacherId);
        List<Need> needs = needDAO.getAll();

        // We consider ContractType - schooInterests - personnalInterests
        String teacherPersonnalInterests = teacher.getPersonnalInterests();
        ContractType teacherContractType = teacher.getContractType();
        String teacherSchoolInterests = teacher.getSchoolInterests();

        return needs.stream()
            // Running needs
            .filter(
                need -> need.getCandidatures()
                    .stream()
                    .noneMatch(candidature -> candidature.getStatus() == StatusType.Accepted))
            // Calculate score : 5 if subject matches / 3 if contract type matches / 1 if school matches
            .map(
                need -> {
                    int score = (
                        (teacherPersonnalInterests != null
                         && teacherPersonnalInterests.toLowerCase().contains(need.getSubject().toLowerCase()) ? 5 : 0)
                        + (need.getContractType() == teacherContractType ? 3 : 0)
                        + (teacherSchoolInterests != null
                           && teacherSchoolInterests.toLowerCase()
                               .contains(need.getSchoolName().getSchoolName().toLowerCase()) ? 1 : 0)
                    );
                  return Map.entry(need, score);
                }
            )
            // Sort by score
            .sorted(Map.Entry.comparingByValue())
            // Max : 10 results
            .limit(10)
            // Transform to list
            .map(Map.Entry::getKey).toList();
    }

    @Override
    public List<Candidature> getCandidatures(int teacherId) throws EntityNotFoundException {
        return candidatureDAO.findAllByTeacher(teacherId);
    }
}
