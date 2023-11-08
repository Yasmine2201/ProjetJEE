package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.DisponibilityDAO;
import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class DisponibilityService {

    @Inject
    DisponibilityDAO disponibilityDAO;

    public Disponibility getDisponibility(int disponibilityId) throws EntityNotFoundException {
        return disponibilityDAO.findById(disponibilityId);
    }

    public Disponibility addDisponibility(Disponibility disponibility) throws EntityExistsException {
        return disponibilityDAO.create(disponibility);
    }

    public Disponibility editDisponibility(Disponibility disponibility) throws EntityNotFoundException {
        return disponibilityDAO.update(disponibility);
    }

    public void deleteDisponibility(Disponibility disponibility) throws EntityNotFoundException {
        disponibilityDAO.delete(disponibility);
    }
}
