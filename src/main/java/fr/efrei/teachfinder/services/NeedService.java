package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.NeedBean;
import fr.efrei.teachfinder.dao.ICandidatureDAO;
import fr.efrei.teachfinder.dao.INeedDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.entities.*;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@Stateless
public class NeedService {

    @Inject INeedDAO needDAO;
    @Inject ICandidatureDAO candidatureDAO;
    @Inject ITeacherDAO teacherDAO;

    @EJB
    RecruiterService recruiterService;
    @EJB
    SchoolService schoolService;

    public Need createNeed(NeedBean needBean)
        throws IncompleteEntityException, EntityExistsException, EntityNotFoundException, IllegalArgumentException {
        if (needBean.getRecruiterId() == null
            || StringUtils.isNullOrEmpty(needBean.getSchoolName())
            || StringUtils.isNullOrEmpty(needBean.getSubject())
        ) {
            throw new IncompleteEntityException("A field is missing or null");
        }

        if (StringUtils.isNullOrEmpty(needBean.getContractType())) needBean.setContractType("Any");

        Need need = mapBeanToNeed(needBean);
        return needDAO.create(need);
    }

    public Need getNeed(int needId) throws EntityNotFoundException {
        Need need = needDAO.findById(needId);
        if (need == null) throw new EntityNotFoundException("Need with id " + needId + " not found.");
        return need;
    }

    public Need updateNeed(NeedBean needBean)
                throws IncompleteEntityException, EntityNotFoundException, IllegalArgumentException {

        if (needBean.getRecruiterId() == null
            || needBean.getNeedId() == null
            || StringUtils.isNullOrEmpty(needBean.getSchoolName())
            || StringUtils.isNullOrEmpty(needBean.getSubject())
        ) {
            throw new IncompleteEntityException("A field is missing or null");
        }

        if (StringUtils.isNullOrEmpty(needBean.getContractType())) needBean.setContractType("Any");

        Need need = mapBeanToNeed(needBean);
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
        CandidatureId candidatureId = new CandidatureId();
        candidatureId.setTeacherId(teacherId);
        candidatureId.setNeedId(needId);

        newCandidature.setId(candidatureId);
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

    public Need mapBeanToNeed(NeedBean needBean)
            throws EntityNotFoundException, IllegalArgumentException {

        Recruiter recruiter = recruiterService.getRecruiter(needBean.getRecruiterId());
        School school = schoolService.getSchool(needBean.getSchoolName());

        Need need = new Need();
        need.setId(needBean.getNeedId());
        need.setRecruiter(recruiter);
        need.setSchoolName(school);
        need.setSubject(needBean.getSubject());
        need.setContractType(ContractType.valueOf(needBean.getContractType()));
        need.setTimePeriod(needBean.getTimePeriod());
        need.setRequirements(needBean.getRequirements());
        need.setNotes(needBean.getNotes());

        return need;
    }
}
