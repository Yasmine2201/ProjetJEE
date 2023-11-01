package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.RegistrationBean;
import fr.efrei.teachfinder.dao.IRegistrationDAO;
import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.entities.StatusType;
import fr.efrei.teachfinder.exceptions.InvalidRegistrationException;
import fr.efrei.teachfinder.exceptions.UnavailableLoginException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;

@Stateless
public class RegistrationService implements IRegistrationService {

    @Inject
    private IRegistrationDAO registrationDAO;

    @EJB
    private ISecurityService securityService;
    @EJB
    private IUserService userService;

    @Override
    public Registration createRegistration(RegistrationBean registration) throws InvalidRegistrationException, UnavailableLoginException {

        List<String> shouldNotBeNullFields = Arrays.asList(
                registration.getLogin(),
                registration.getPassword(),
                registration.getFirstname(),
                registration.getLastname(),
                registration.getEmail(),
                registration.getRole()
        );

        if (registration.getRole() != null && registration.getRole().equals("Teacher"))
            shouldNotBeNullFields.add(registration.getSchoolName());

        boolean isRegistrationValid = shouldNotBeNullFields.stream().allMatch(StringUtils::isNotNullOrEmpty);
        if (!isRegistrationValid) {
            throw new InvalidRegistrationException();
        }

        if (registrationWithLoginExists(registration.getLogin())) {
            throw new UnavailableLoginException();
        }

        School school = null;
        if (registration.getSchoolName() != null){
            school = new School();
            school.setSchoolName(registration.getSchoolName());
        }

        Registration registrationToCreate = new Registration();
        registrationToCreate.setLogin(registration.getLogin());
        registrationToCreate.setPassword(securityService.hashPassword(registration.getPassword()));
        registrationToCreate.setFirstname(registration.getFirstname());
        registrationToCreate.setLastname(registration.getLastname());
        registrationToCreate.setEmail(registration.getEmail());
        registrationToCreate.setPhone(registration.getPhone());
        registrationToCreate.setRole(RoleType.valueOf(registration.getRole()));
        registrationToCreate.setSchoolName(school);
        registrationToCreate.setStatus(StatusType.Pending);

        return registrationDAO.create(registrationToCreate);
    }

    @Override
    public List<Registration> getPendingRegistration() {
        return registrationDAO.getAllWithStatus(StatusType.Pending);
    }

    @Override
    public void denyRegistration(int registrationId) {
        Registration reg = registrationDAO.findById(registrationId);
        reg.setStatus(StatusType.Refused);
    }

    @Override
    public void approveRegistration(int registrationId) {
        Registration reg = registrationDAO.findById(registrationId);
        reg.setStatus(StatusType.Accepted);
    }

    @Override
    public boolean registrationWithLoginExists(String login) {
        List<Registration> registrations = registrationDAO.findAllWithLogin(login);

        return registrations.stream().anyMatch(registration -> {
            StatusType status = registration.getStatus();
            return status == StatusType.Accepted || status == StatusType.Pending;
        });
    }
}
