package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.DisponibilityDAO;
import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class DisponibilityService implements IDisponibilityService{

    @Inject
    DisponibilityDAO disponibilityDAO;

    @Override
    public Disponibility getDisponibility(int disponibilityId) throws EntityNotFoundException {
        return disponibilityDAO.findById(disponibilityId);
    }

    @Override
    public Disponibility addDisponibility(Disponibility disponibility) throws EntityExistsException {
        return disponibilityDAO.create(disponibility);
    }

    @Override
    public Disponibility editDisponibility(Disponibility disponibility) throws EntityNotFoundException {
        return disponibilityDAO.update(disponibility);
    }

    @Override
    public void deleteDisponibility(Disponibility disponibility) throws EntityNotFoundException {
        disponibilityDAO.delete(disponibility);
    }
}
