package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.School;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.SCHOOL_FINDBYNAME;

public class SchoolDAO implements ISchoolDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public School findByName(String schoolName) {
        TypedQuery<School> query = entityManager
                .createQuery(SCHOOL_FINDBYNAME, School.class)
                .setParameter("schoolName", schoolName);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public School create(School school) throws EntityExistsException {
        // TODO
        return null;
    }

    @Override
    public List<School> getAll() {
        // TODO
        return null;
    }
}
