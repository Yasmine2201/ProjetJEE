package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.IRecruiterDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.dao.IUserDAO;
import fr.efrei.teachfinder.entities.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Stateless
public class UserService implements IUserService {

    @Inject
    private IUserDAO userDAO;

    @Inject
    private ITeacherDAO teacherDAO;

    @Inject
    private IRecruiterDAO recruiterDAO;

    @Override
    public ApplicationUser getUser(int userId) throws EntityNotFoundException {
        ApplicationUser user = userDAO.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }

        return user;
    }

    @Override
    public void updateUser(ApplicationUser user) throws EntityNotFoundException {
        userDAO.update(user);
    }

    @Override
    public boolean userWithLoginExists(String login) {
        return userDAO.findByLogin(login) != null;
    }

    @Override
    public ApplicationUser createUserFromRegistration(Registration registration) throws EntityExistsException {
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
            teacher.setApplicationuser(createdUser);
            teacherDAO.create(teacher);
        } else if (createdUser.getRole() == RoleType.Recruiter) {
            Recruiter recruiter = new Recruiter();
            recruiter.setApplicationuser(createdUser);
            recruiterDAO.create(recruiter);
        }

        return createdUser;
    }
}
