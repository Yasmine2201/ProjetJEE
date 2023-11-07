package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.CandidatureDAO;
import fr.efrei.teachfinder.dao.NeedDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
@Stateless
public class NeedService implements INeedService{

    @Inject
    NeedDAO needDAO;

    @Inject
    CandidatureDAO candidatureDAO;
    @Inject
    TeacherDAO teacherDAO;

    @Override
    public Need getNeed(int needId)throws EntityNotFoundException {
        return needDAO.findById(needId) ;
    }

    @Override
    public Need updateNeed(Need need) throws EntityNotFoundException {
        return needDAO.update(need);
    }

    @Override
    public Candidature apply(int needId, int teacherId) {
        Need need=needDAO.findById(needId);
        Teacher teacher=teacherDAO.findById(teacherId);
        if(need!=null){
            School school= need.getSchoolName();

            if(teacher != null ){
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
            else {return null;}
            }

        else{ return null;}
    }

}
