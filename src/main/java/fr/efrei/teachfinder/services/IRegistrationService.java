package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Registration;

import java.util.List;

public interface IRegistrationService {

    List<Registration> getPendingRegistration();

    void denyRegistration(int registrationId);

    void approveRegistration(int registrationId);
}
