package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Registration;
import fr.efrei.teachfinder.entities.Teacher;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.*;

public class TeacherDAO implements ITeacherDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Teacher findById(int teacherId) {
        TypedQuery<Teacher> query = entityManager
                .createQuery(TEACHER_FINDBYID, Teacher.class)
                .setParameter("teacherId", teacherId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public Teacher create(Teacher teacher) throws EntityExistsException {
        if (findById(teacher.getId()) != null)
            throw new EntityExistsException("Teacher already exists");

        entityManager.getTransaction().begin();
        Teacher createdTeacher = entityManager.merge(teacher);
        entityManager.getTransaction().commit();

        return createdTeacher;
    }

    @Override
    public Teacher update(Teacher teacher) throws EntityNotFoundException {
        try {
            Teacher existingTeacher = findById(teacher.getId());

            if (existingTeacher == null) {
                throw new EntityNotFoundException("Teacher with ID " + teacher.getId() + " not found");
            }

            entityManager.getTransaction().begin();
            Teacher updatedTeacher = entityManager.merge(existingTeacher);
            entityManager.getTransaction().commit();
            return updatedTeacher;
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public List<Teacher> getAll() {

        TypedQuery<Teacher> query = entityManager.createQuery(TEACHER_GETALL, Teacher.class);

        return query.getResultList();

    }

}
