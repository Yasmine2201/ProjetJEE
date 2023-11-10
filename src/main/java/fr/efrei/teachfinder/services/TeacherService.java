package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.NeedBean;
import fr.efrei.teachfinder.beans.TeacherBean;
import fr.efrei.teachfinder.dao.IDisponibilityDAO;
import fr.efrei.teachfinder.dao.IEvaluationDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.entities.*;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TeacherService {
    @Inject
    ITeacherDAO teacherDAO;
    @Inject
    IDisponibilityDAO disponibilityDAO;
    @Inject
    IEvaluationDAO evaluationDAO;





    public Teacher getTeacher(int teacherId)  throws EntityNotFoundException {
        Teacher teacher = teacherDAO.findById(teacherId);
        if (teacher == null) throw new EntityNotFoundException("Teacher with id " + teacherId + " not found.");
        return teacher;
    }

    public Teacher updateTeacher(TeacherBean teacherBean)  throws EntityNotFoundException{
            Teacher teacher = mapBeanToTeacher(teacherBean);
            return teacherDAO.update(teacher);

    }
    public List<Disponibility> getTeacherFutureDisponibilities(int teacherId) throws EntityNotFoundException {
        if (teacherNotExists(teacherId)) {
            throw new EntityNotFoundException("No teacher found with id " + teacherId);
        }

        List<Disponibility> disponibilities = disponibilityDAO.findAllByTeacher(teacherId);
        LocalDateTime todayDate = LocalDateTime.now();


        List<Disponibility> filteredDisponibilities = new ArrayList<>();


        for (Disponibility disponibility : disponibilities) {
            if (disponibility.getEndDate().isAfter(todayDate)) {
                filteredDisponibilities.add(disponibility);
            }
        }
        return filteredDisponibilities;
    }

    public List<Evaluation> getTeacherEvaluations(int teacherId) throws EntityNotFoundException {
        if (teacherNotExists(teacherId)) {
            throw new EntityNotFoundException("No teacher found with id " + teacherId);
        }
        else{
            return evaluationDAO.findAllByTeacher(teacherId);
        }
    }
    public Teacher mapBeanToTeacher(TeacherBean teacherBean)
            throws EntityNotFoundException, IllegalArgumentException {

      Teacher teacher = new Teacher();
      teacher.setId(teacherBean.getTeacherId());
      teacher.setExperiences(teacherBean.getExperiences());
      teacher.setSkills(teacherBean.getSkills());
      teacher.setPersonnalInterests(teacherBean.getPersonnalInterests());
      teacher.setSchoolInterests(teacherBean.getSchoolInterests());
      teacher.setDesiredLevels((teacherBean.getDesiredLevels()));
      teacher.setContractType(ContractType.valueOf(teacherBean.getContractType()));
      teacher.setAcademicCertifications(teacherBean.getAcademicCertifications());
      teacher.setOtherInformations(teacherBean.getOtherInformations());
      teacher.setRecommendations(teacherBean.getRecommendations());
      return teacher;
    }
    boolean teacherNotExists(int teacherId){ return teacherDAO.findById(teacherId) == null;}
}
