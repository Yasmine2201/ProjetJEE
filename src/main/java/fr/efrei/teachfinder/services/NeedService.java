package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.*;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@Stateless
public class NeedService {

    @Inject
    NeedDAO needDAO;

    @Inject
    CandidatureDAO candidatureDAO;
    @Inject
    TeacherDAO teacherDAO;

    public Need getNeed(int needId) throws EntityNotFoundException {
        Need need = needDAO.findById(needId);
        if (need == null) throw new EntityNotFoundException("Need with id " + needId + " not found.");
        return need;
    }

    public Need updateNeed(Need need) throws EntityNotFoundException {
        return needDAO.update(need);
    }

    public Candidature apply(int needId, int teacherId) throws EntityExistsException, EntityNotFoundException {
        Need need = needDAO.findById(needId);
        Teacher teacher = teacherDAO.findById(teacherId);

        if (teacher == null) {
            throw new EntityNotFoundException("Teacher with id " + teacherId + " not found.");
        }
        if (need == null) {
            throw new EntityNotFoundException("Need with id " + needId + " not found.");
        }

        School school = need.getSchoolName();

        Candidature newCandidature = new Candidature();
        newCandidature.setTeacher(teacher);
        newCandidature.setNeed(need);
        newCandidature.setSchoolName(school);
        newCandidature.setStatus(StatusType.Pending);
        newCandidature.setCreatedOn(LocalDateTime.now());
        newCandidature.setIsInitiatedByTeacher(true);
        newCandidature.setIsValidatedByRecruiter(false);
        newCandidature.setIsValidatedByTeacher(false);
        return candidatureDAO.create(newCandidature);
    }
}
