package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Teacher;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.QueryRequests;

public class TeacherDAO implements ITeacherDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Teacher findById(int teacherId) {
        TypedQuery<Teacher> query = entityManager
                .createQuery(QueryRequests.TEACHER_FINDBYID, Teacher.class)
                .setParameter("teacherId", teacherId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Teacher create(Teacher teacher) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            Teacher createdTeacher = entityManager.merge(teacher);
            entityManager.getTransaction().commit();
            return createdTeacher;
        } catch (EntityExistsException ex) {
            throw new EntityExistsException("Teacher already exists");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws EntityNotFoundException {

        Teacher existingTeacher = findById(teacher.getId());

        if (existingTeacher == null) {
            throw new EntityNotFoundException("Teacher with ID " + teacher.getId() + " not found");
        }

        entityManager.getTransaction().begin();
        Teacher updatedTeacher = entityManager.merge(existingTeacher);
        entityManager.getTransaction().commit();
        return updatedTeacher;
    }

    @Override
    public List<Teacher> getAll() {

        TypedQuery<Teacher> query = entityManager.createQuery(QueryRequests.TEACHER_GETALL, Teacher.class);

        return query.getResultList();
    }

    @Override
    public List<Teacher> searchWithSkills(String research) {
        TypedQuery<Teacher> query = entityManager.createQuery(
                        QueryRequests.TEACHER_SEARCHWITH_SKILLS, Teacher.class)
                .setParameter("search", research);

        return query.getResultList();
    }
}
