package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.DisponibilityDAO;
import fr.efrei.teachfinder.entities.Disponibility;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class DisponibilityService implements IDisponibilityService{

    @Inject
    DisponibilityDAO disponibilityDAO;

    @Override
    public Disponibility getDisponibility(int disponibilityId) {
        return disponibilityDAO.findById(disponibilityId);
    }

    @Override
    public Disponibility addDisponibility(Disponibility disponibility) {
        return disponibilityDAO.create(disponibility);
    }

    @Override
    public Disponibility editDisponibility(Disponibility disponibility) {
        return disponibilityDAO.update(disponibility);
    }

    @Override
    public void deletDisponibility(Disponibility disponibility) {
        disponibilityDAO.delete(disponibility);
    }
}
