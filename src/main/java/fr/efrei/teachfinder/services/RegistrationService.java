package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IRegistrationDAO;
import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class RegistrationService implements IRegistrationService{

    @Inject
    private IRegistrationDAO registrationDAO;

    @Override
    public List<Registration> getPendingRegistration() {
        return registrationDAO.getAllWithStatus(StatusType.Pending);
    }

    @Override
    public void denyRegistration(int registrationId) {
        Registration reg=registrationDAO.findById(registrationId);
        reg.setStatus(StatusType.Refused);
    }

    @Override
    public void approveRegistration(int registrationId) {
        Registration reg=registrationDAO.findById(registrationId);
        reg.setStatus(StatusType.Accepted);
    }
}
