package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Disponibility;

public interface IDisponibilityService {

    Disponibility getDisponibility(int disponibilityId);

    Disponibility addDisponibility(Disponibility disponibility);

    Disponibility editDisponibility(Disponibility disponibility);

    void deletDisponibility(Disponibility disponibility);
}
