package fr.efrei.teachfinder.dao;


import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.QueryRequests;

public class SchoolDAO implements ISchoolDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public School findByName(String schoolName) {
        TypedQuery<School> query = entityManager
                .createQuery(QueryRequests.SCHOOL_FINDBYNAME, School.class)
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
        TypedQuery<School> query = entityManager.createQuery(QueryRequests.SCHOOL_GETALL, School.class);

        return query.getResultList();
    }

    @Override
    public List<Recruiter> findAllRecruiters(String schoolName) {
        TypedQuery<Recruiter> query = entityManager.createQuery(
                        QueryRequests.RECRUITER_FINDALL, Recruiter.class)
                .setParameter("schoolName", schoolName);

        return query.getResultList();
    }

    @Override
    public void update(School school) throws EntityNotFoundException {
        if (findByName(school.getSchoolName()) == null)
            throw new EntityNotFoundException("No School found with name " + school.getSchoolName());

        entityManager.getTransaction().begin();
        entityManager.merge(school);
        entityManager.getTransaction().commit();
    }
    public List<School> searchWithString(String research) {
        TypedQuery<School> query = entityManager.createQuery(
                        QueryRequests.SCHOOL_SEARCHWITH_STRING, School.class)
                .setParameter("search", research);

        return query.getResultList();
    }
}
