package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IRegistrationDAO;
import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.StatusType;
import jakarta.ejb.EJB;

import java.util.List;

public class RegistrationService implements IRegistrationService{

    @EJB
    IRegistrationDAO registrationDAO;

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
