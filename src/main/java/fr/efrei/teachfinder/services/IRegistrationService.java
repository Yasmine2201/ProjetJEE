package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.RegistrationBean;
import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.exceptions.UnavailableLoginException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IRegistrationService {

    Registration createRegistration(RegistrationBean registration)
            throws IncompleteEntityException, UnavailableLoginException, IllegalArgumentException;

    List<Registration> getPendingRegistrations();

    void denyRegistration(int registrationId) throws EntityNotFoundException;

    void approveRegistration(int registrationId) throws EntityNotFoundException, EntityExistsException;

    boolean registrationWithLoginExists(String login);
}
