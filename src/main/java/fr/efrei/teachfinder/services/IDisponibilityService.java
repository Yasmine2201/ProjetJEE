package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

public interface IDisponibilityService {

    Disponibility getDisponibility(int disponibilityId) throws EntityNotFoundException;

    Disponibility addDisponibility(Disponibility disponibility) throws EntityExistsException;

    Disponibility editDisponibility(Disponibility disponibility)throws EntityNotFoundException;

    void deleteDisponibility(Disponibility disponibility) throws EntityNotFoundException;
}
