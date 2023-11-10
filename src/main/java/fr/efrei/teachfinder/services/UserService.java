package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IRecruiterDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.dao.IUserDAO;
import fr.efrei.teachfinder.entities.*;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.utils.StringUtils;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserService {

    @Inject private IUserDAO userDAO;
    @Inject private ITeacherDAO teacherDAO;
    @Inject private IRecruiterDAO recruiterDAO;
    @Inject private SecurityService securityService;

    @EJB private SchoolService schoolService;

    public ApplicationUser getUser(int userId) throws EntityNotFoundException {
        ApplicationUser user = userDAO.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }

        return user;
    }

    public void updateUser(ApplicationUser user) throws EntityNotFoundException, IncompleteEntityException {

        Integer userId = user.getId();
        String login = user.getLogin();
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String password = user.getPassword();
        String email = user.getEmail();
        String phone = user.getPhone();

        if (user.getId() == null
        || StringUtils.isNullOrEmpty(login)
        || StringUtils.isNullOrEmpty(firstname)
        || StringUtils.isNullOrEmpty(lastname)
        || StringUtils.isNullOrEmpty(email)
        ) throw new IncompleteEntityException("Missing fields in user");

        ApplicationUser oldUser = userDAO.findById(userId);
        if (oldUser == null) throw new EntityNotFoundException(
                "No ApplicationUser with id " + userId + " found.");

        user.setPassword(StringUtils.isNullOrEmpty(password) ? oldUser.getPassword() : securityService.hashPassword(password));
        user.setRole(oldUser.getRole());
        userDAO.update(user);
    }

    public boolean userWithLoginExists(String login) {
        return userDAO.findByLogin(login) != null;
    }

    public void createUserFromRegistration(Registration registration) throws EntityExistsException {
        ApplicationUser user = new ApplicationUser();
        user.setLogin(registration.getLogin());
        user.setPassword(registration.getPassword());
        user.setFirstname(registration.getFirstname());
        user.setLastname(registration.getLastname());
        user.setEmail(registration.getEmail());
        user.setPhone(registration.getPhone());
        user.setRole(registration.getRole());
        ApplicationUser createdUser = userDAO.create(user);

        if (createdUser.getRole() == RoleType.Teacher) {
            Teacher teacher = new Teacher();
            teacher.setId(createdUser.getId());
            teacher.setApplicationuser(createdUser);
            teacherDAO.create(teacher);
        } else if (createdUser.getRole() == RoleType.Recruiter) {
            Recruiter recruiter = new Recruiter();
            recruiter.setId(createdUser.getId());
            recruiter.setApplicationuser(createdUser);
            recruiter.setSchoolName(registration.getSchoolName());
            recruiterDAO.create(recruiter);
        }
    }
}
