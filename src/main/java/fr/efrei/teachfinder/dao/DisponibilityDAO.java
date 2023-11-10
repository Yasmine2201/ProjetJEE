package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Disponibility;
import fr.efrei.teachfinder.exceptions.EntityExistsException;
import fr.efrei.teachfinder.exceptions.EntityNotFoundException;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.PERSISTENCE_UNIT_NAME;
import static fr.efrei.teachfinder.utils.Constants.QueryRequests;

public class DisponibilityDAO implements IDisponibilityDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Disponibility findById(int disponibilityId) {
        TypedQuery<Disponibility> query = entityManager
                .createQuery(QueryRequests.DISPONIBILITY_FINDBYID, Disponibility.class)
                .setParameter("disponibilityId", disponibilityId);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Disponibility create(Disponibility disponibility) throws EntityExistsException {
        if (disponibility.getId() != null && findById(disponibility.getId()) != null) throw new EntityExistsException("Disponibility already exists");
        entityManager.getTransaction().begin();
        Disponibility createdDisponibility = entityManager.merge(disponibility);
        entityManager.getTransaction().commit();

        return createdDisponibility;
    }

    @Override
    public List<Disponibility> findAllByTeacher(int teacherId) {
        TypedQuery<Disponibility> query = entityManager.createQuery(
                        QueryRequests.DISPONIBILITY_FINDALL_BY_TEACHER, Disponibility.class)
                .setParameter("teacherId", teacherId);

        return query.getResultList();
    }

    @Override
    public Disponibility update(Disponibility disponibility) throws EntityNotFoundException {
        Disponibility existingDisponibility = findById(disponibility.getId());
        if (existingDisponibility == null) {
            throw new EntityNotFoundException("Disponibility with ID " + disponibility.getId() + " not found");
        }
        entityManager.getTransaction().begin();
        Disponibility updatedDisponibility = entityManager.merge(disponibility);
        entityManager.getTransaction().commit();
        return updatedDisponibility;

    }

    @Override
    public void delete(Disponibility disponibility) throws EntityNotFoundException {
        entityManager.getTransaction().begin();
        Disponibility existingDisponibility = findById(disponibility.getId());
        if (existingDisponibility == null) {
            entityManager.getTransaction().rollback(); // cancel if it doesn't exist
            throw new EntityNotFoundException("Disponibility with ID " + disponibility.getId() + " not found");
        }
        entityManager.remove(existingDisponibility);
        entityManager.getTransaction().commit();
    }
}