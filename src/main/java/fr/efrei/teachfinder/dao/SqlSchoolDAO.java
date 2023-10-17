package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.School;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.SCHOOL_FINDBYID;

public class SqlSchoolDAO implements ISchoolDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public School findById(int schoolId) {
        TypedQuery<School> query = entityManager
                .createQuery(SCHOOL_FINDBYID, School.class)
                .setParameter("schoolId", schoolId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        // TODO byName
    }

    @Override
    public School create(School school) throws EntityExistsException {
        return null;
    }

    @Override
    public List<School> getAll() {
        return null;
    }
}
