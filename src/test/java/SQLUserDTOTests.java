import fr.efrei.teachfinder.dao.SqlUserDAO;
import fr.efrei.teachfinder.entities.ApplicationUser;
import static org.junit.jupiter.api.Assertions.*;

import fr.efrei.teachfinder.entities.Registration;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;

public class SQLUserDTOTests {
    private static final SqlUserDAO userDAO = new SqlUserDAO();

//    @Test
//    public void createUser_NotExistingUser_ReturnsUser() {
//        // Given
//        Registration registration = new Registration();
//        registration.setLogin("adminThibaut");
//        registration.setName("Thibaut COLNOT");
//        registration.setMail("thibaut.colnot@efrei.net");
//        registration.setRole(Registration.Role.Recruiter);
//        // ImASuperAdmin
//        registration.setPassword("49a02abc531d047c7596bcdd3657e213db6d5d2972ca44d7699ea4accc1827c2");
//        registration.setStatus(Registration.Status.Accepted);
//
//        // When
//        ApplicationUser userCreated = userDAO.create(registration);
//
//        // Then
//        assertNotNull(userCreated);
//        assertEquals("adminThibaut", userCreated.getLogin());
//        assertEquals("Thibaut COLNOT", userCreated.getName());
//        assertEquals("thibaut.colnot@efrei.net", userCreated.getMail());
//        assertEquals(ApplicationUser.Role.Recruiter, userCreated.getRole());
//        assertEquals("49a02abc531d047c7596bcdd3657e213db6d5d2972ca44d7699ea4accc1827c2", userCreated.getPassword());
//    }

    @Test
    public void createUser_ExistingUser_ThrowsEntityExistsException() {
        // Given
        Registration registration = new Registration();
        registration.setLogin("adminThibaut");
        registration.setName("Thibaut COLNOT");
        registration.setMail("thibaut.colnot@efrei.net");
        registration.setRole(Registration.Role.Recruiter);
        // ImASuperAdmin
        registration.setPassword("0000000000000000000000000000000000000000000000000000000000000000");
        registration.setStatus(Registration.Status.Accepted);

        // When
        assertThrows(EntityExistsException.class, () -> userDAO.create(registration));
    }

    @Test
    public void getUserByLogin_ExistingUser_ReturnsUser() {
        ApplicationUser user = userDAO.findByLogin("adminThibaut");
        assertNotNull(user);
        assertEquals(1, user.getUserId());
        assertEquals("adminThibaut", user.getLogin());
        assertEquals("Thibaut COLNOT", user.getName());
        assertEquals("thibaut.colnot@efrei.net", user.getMail());
        assertEquals(ApplicationUser.Role.Admin, user.getRole());
    }

    @Test
    public void getUserByLogin_NonExistingUser_ReturnsNull() {
        ApplicationUser user = userDAO.findByLogin("ProbablyDoesNotExist_1234?");
        assertNull(user);
    }
}
