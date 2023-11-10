package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.DisponibilityBean;
import fr.efrei.teachfinder.dao.IDisponibilityDAO;
import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.entities.Teacher;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Stateless
public class DisponibilityService {

    @Inject
    IDisponibilityDAO disponibilityDAO;
    @Inject
    TeacherService teacherService;

    public Disponibility getDisponibility(int disponibilityId) throws EntityNotFoundException {
        Disponibility disponibility = disponibilityDAO.findById(disponibilityId);
        if (disponibility == null) throw new EntityNotFoundException("Disponibility with id " + disponibilityId + " not found.");
        return disponibility;
    }

    public Disponibility createDisponibility(DisponibilityBean disponibilityBean)
        throws EntityExistsException, EntityNotFoundException, IncompleteEntityException, IllegalArgumentException {

        if (disponibilityBean.getTeacherId() == null
            || StringUtils.isNullOrEmpty(disponibilityBean.getStartDate())
            || StringUtils.isNullOrEmpty(disponibilityBean.getEndDate())) {
            throw new IncompleteEntityException("A field is missing or null");
        }

        Disponibility disponibility = mapBeanToDisponibility(disponibilityBean);
        return disponibilityDAO.create(disponibility);
    }

    public Disponibility editDisponibility(DisponibilityBean disponibilityBean)
        throws EntityNotFoundException, IncompleteEntityException, IllegalArgumentException {

        if (disponibilityBean.getDisponibilityId() == null
            || disponibilityBean.getTeacherId() == null
            || StringUtils.isNullOrEmpty(disponibilityBean.getStartDate())
            || StringUtils.isNullOrEmpty(disponibilityBean.getEndDate())) {
            throw new IncompleteEntityException("A field is missing or null");
        }

        Disponibility disponibility = mapBeanToDisponibility(disponibilityBean);
        return disponibilityDAO.update(disponibility);
    }

    public Disponibility mapBeanToDisponibility(DisponibilityBean disponibilityBean)
        throws EntityNotFoundException, IllegalArgumentException {

        Disponibility disponibility = new Disponibility();

        try {
            LocalDateTime startDate = LocalDateTime.parse(disponibilityBean.getStartDate());
            LocalDateTime endDate = LocalDateTime.parse(disponibilityBean.getEndDate());

            Teacher teacher = teacherService.getTeacher(disponibilityBean.getTeacherId());

            disponibility.setId(disponibilityBean.getDisponibilityId());
            disponibility.setTeacher(teacher);
            disponibility.setStartDate(startDate);
            disponibility.setEndDate(endDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Incorrect date format");
        }

        return disponibility;
    }

    public void deleteDisponibility(Disponibility disponibility) throws EntityNotFoundException {
        disponibilityDAO.delete(disponibility);
    }
}
