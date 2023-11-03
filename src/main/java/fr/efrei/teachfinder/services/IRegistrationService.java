package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.RegistrationBean;
import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.exceptions.UnavailableLoginException;

import java.util.List;

public interface IRegistrationService {

    Registration createRegistration(RegistrationBean registration)
            throws IncompleteEntityException, UnavailableLoginException, IllegalArgumentException;

    List<Registration> getPendingRegistration();

    void denyRegistration(int registrationId);

    void approveRegistration(int registrationId);

    boolean registrationWithLoginExists(String login);
}
