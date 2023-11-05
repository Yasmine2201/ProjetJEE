package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.beans.RegistrationBean;
import fr.efrei.teachfinder.dao.IRegistrationDAO;
import fr.efrei.teachfinder.dao.IUserDAO;
import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.RoleType;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.entities.StatusType;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.exceptions.UnavailableLoginException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class RegistrationService implements IRegistrationService {

    @Inject
    private IUserDAO userDAO;

    @Inject
    private IRegistrationDAO registrationDAO;

    @EJB
    private ISecurityService securityService;

    @EJB IUserService userService;

    @Override
    public Registration createRegistration(RegistrationBean registration) throws IncompleteEntityException, UnavailableLoginException {

        List<String> shouldNotBeNullFields = new ArrayList<>(Arrays.asList(
                registration.getLogin(),
                registration.getPassword(),
                registration.getFirstname(),
                registration.getLastname(),
                registration.getEmail(),
                registration.getRole()
        ));

        String login = registration.getLogin();
        String roleString = registration.getRole();

        if (roleString != null && roleString.equals("Recruiter")) {
            shouldNotBeNullFields.add(registration.getSchoolName());
        }

        boolean isRegistrationValid = shouldNotBeNullFields.stream().allMatch(StringUtils::isNotNullOrEmpty);
        if (!isRegistrationValid) {
            throw new IncompleteEntityException();
        }

        if (registrationWithLoginExists(login) || userService.userWithLoginExists(login)) {
            throw new UnavailableLoginException();
        }

        School school = null;
        if (StringUtils.isNotNullOrEmpty(registration.getSchoolName())){
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
        registrationToCreate.setRole(RoleType.valueOf(roleString));
        registrationToCreate.setSchoolName(school);
        registrationToCreate.setStatus(StatusType.Pending);

        return registrationDAO.create(registrationToCreate);
    }

    @Override
    public List<Registration> getPendingRegistration() {
        return registrationDAO.getAllWithStatus(StatusType.Pending);
    }

    @Override
    public void denyRegistration(int registrationId) throws EntityNotFoundException {
        registrationDAO.changeStatus(registrationId, StatusType.Refused);
    }

    @Override
    public void approveRegistration(int registrationId) throws EntityNotFoundException, EntityExistsException {
        registrationDAO.changeStatus(registrationId, StatusType.Accepted);
        Registration registration = registrationDAO.findById(registrationId);
        userService.createUserFromRegistration(registration);
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
