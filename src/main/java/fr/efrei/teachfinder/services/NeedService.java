package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.*;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

public class NeedService implements INeedService{

    @Inject
    NeedDAO needDAO;

    @Inject
    CandidatureDAO candidatureDAO;
    @Inject
    TeacherDAO teacherDAO;

    @Override
    public Need getNeed(int needId) {
        return needDAO.findById(needId) ;
    }

    @Override
    public Need updateNeed(Need need) {
        return needDAO.update(need);
    }

    @Override
    public Candidature apply(int needId, int teacherId) {
        Need need=needDAO.findById(needId);
        Teacher teacher=teacherDAO.findById(teacherId);
        School school= need.getSchoolName();
        if(teacher != null && need != null){

        Candidature newCandidature = new Candidature();
        newCandidature.setTeacher(teacher);
        newCandidature.setNeed(need);
        newCandidature.setSchoolName(school);
        newCandidature.setStatus(StatusType.Pending);
        newCandidature.setCreatedOn(LocalDateTime.now());
        newCandidature.setIsInitiatedByTeacher(true);
        newCandidature.setIsValidatedByRecruiter(false);
        newCandidature.setIsValidatedByTeacher(false);

        return candidatureDAO.create(newCandidature);}
        else{ return null;}
    }


}
