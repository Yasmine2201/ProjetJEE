package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.*;

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
        try {
            entityManager.getTransaction().begin();
            School createdSchool = entityManager.merge(school);
            entityManager.getTransaction().commit();
            return createdSchool;
        }
        catch (EntityExistsException ex){
            throw new EntityExistsException("School already exists");
        }
    }

    @Override
    public List<School> getAll() {
        TypedQuery<School> query = entityManager.createQuery(SCHOOL_GETALL, School.class);

        return query.getResultList();
    }

    @Override
    public List<Recruiter> findAllRecruiters(String schoolName) {
        TypedQuery<Recruiter> query = entityManager.createQuery(
                        FINDALL_RECRUITERS, Recruiter.class)
                .setParameter("schoolName", schoolName);

        return query.getResultList();
    }
}
