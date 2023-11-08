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

    public void createDisponibility(DisponibilityBean disponibilityBean)
        throws EntityExistsException, EntityNotFoundException, IncompleteEntityException, IllegalArgumentException {

        if (disponibilityBean.getTeacherId() == null
            || StringUtils.isNullOrEmpty(disponibilityBean.getStartDate())
            || StringUtils.isNullOrEmpty(disponibilityBean.getEndDate())) {
            throw new IncompleteEntityException("A field is missing or null");
        }

        Disponibility disponibility = mapBeanToDisponibility(disponibilityBean);
        disponibilityDAO.create(disponibility);
    }

    public void editDisponibility(DisponibilityBean disponibilityBean)
        throws EntityNotFoundException, IncompleteEntityException, IllegalArgumentException {

        if (disponibilityBean.getDisponibilityId() == null
            || disponibilityBean.getTeacherId() == null
            || StringUtils.isNullOrEmpty(disponibilityBean.getStartDate())
            || StringUtils.isNullOrEmpty(disponibilityBean.getEndDate())) {
            throw new IncompleteEntityException("A field is missing or null");
        }

        Disponibility disponibility = mapBeanToDisponibility(disponibilityBean);
        disponibilityDAO.update(disponibility);
    }

    public Disponibility mapBeanToDisponibility(DisponibilityBean disponibilityBean)
        throws EntityNotFoundException, IllegalArgumentException {

        Disponibility disponibility = new Disponibility();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
            LocalDateTime startDate = LocalDateTime.parse(disponibilityBean.getStartDate(), formatter);
            LocalDateTime endDate = LocalDateTime.parse(disponibilityBean.getEndDate(), formatter);

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
