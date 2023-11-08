package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IRecruiterDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.dao.IUserDAO;
import fr.efrei.teachfinder.entities.*;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class UserService {

    @Inject private IUserDAO userDAO;
    @Inject private ITeacherDAO teacherDAO;
    @Inject private IRecruiterDAO recruiterDAO;

    @EJB private SchoolService schoolService;

    public ApplicationUser getUser(int userId) throws EntityNotFoundException {
        ApplicationUser user = userDAO.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }

        return user;
    }

    public void updateUser(ApplicationUser user) throws EntityNotFoundException {
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
