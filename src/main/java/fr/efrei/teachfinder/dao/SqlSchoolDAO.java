package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.ApplicationUser;
import fr.efrei.teachfinder.entities.School;
import jakarta.persistence.*;

import static fr.efrei.teachfinder.utils.Constants.*;

public class SqlSchoolDAO implements ISchoolDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public School SCHOOL_FINDBYID(int schoolId) {
        TypedQuery<School> query = entityManager
                .createQuery(SCHOOL_FINDBYID, School.class)
                .setParameter("schoolId", schoolId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

    }
}
