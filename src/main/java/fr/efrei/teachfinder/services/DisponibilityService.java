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

    public void createDisponibility(DisponibilityBean disponibility)
        throws EntityExistsException, EntityNotFoundException, IncompleteEntityException, IllegalArgumentException {
        if (disponibility.getTeacherId() == null
            || StringUtils.isNullOrEmpty(disponibility.getStartDate())
            || StringUtils.isNullOrEmpty(disponibility.getEndDate())) {
            throw new IncompleteEntityException("A field is missing or null");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
            LocalDateTime startDate = LocalDateTime.parse(disponibility.getStartDate(), formatter);
            LocalDateTime endDate = LocalDateTime.parse(disponibility.getEndDate(), formatter);

            Teacher teacher = teacherService.getTeacher(disponibility.getTeacherId());

            Disponibility disponibilityCreated = new Disponibility();
            disponibilityCreated.setId(disponibility.getDisponibilityId());
            disponibilityCreated.setTeacher(teacher);
            disponibilityCreated.setStartDate(startDate);
            disponibilityCreated.setEndDate(endDate);

            disponibilityDAO.create(disponibilityCreated);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Incorrect date format");
        }
    }

    public Disponibility editDisponibility(Disponibility disponibility) throws EntityNotFoundException {
        return disponibilityDAO.update(disponibility);
    }

    public void deleteDisponibility(Disponibility disponibility) throws EntityNotFoundException {
        disponibilityDAO.delete(disponibility);
    }
}
