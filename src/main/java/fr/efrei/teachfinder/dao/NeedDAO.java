package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Need;
import jakarta.persistence.*;

import java.util.List;

import static fr.efrei.teachfinder.utils.Constants.*;

public class NeedDAO implements INeedDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Need findById(int needId) {
        TypedQuery<Need> query = entityManager
                .createQuery(NEED_FINDBYID, Need.class)
                .setParameter("needId", needId);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Need create(Need need) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            Need createdNeed = entityManager.merge(need);
            entityManager.getTransaction().commit();

            return createdNeed;
        } catch (EntityExistsException ex) {
            throw new EntityExistsException("Need already exists");
        }

    }

    @Override
    public Need update(Need need) throws EntityNotFoundException {

            Need existingNeed = findById(need.getId());
            if (existingNeed == null) {
                throw new EntityNotFoundException("Need with ID " + need.getId() + " not found");
            }
            entityManager.getTransaction().begin();
            Need updatedNeed = entityManager.merge(existingNeed);
            entityManager.getTransaction().commit();
            return updatedNeed;

    }

    @Override
    public List<Need> findAllBySchool(String schoolName) {
        TypedQuery<Need> query = entityManager.createQuery(
                        FINDALL_BY_SCHOOL, Need.class)
                .setParameter("schoolName", schoolName);

        return query.getResultList();
    }

    @Override
    public List<Need> findAllByRecruiter(int recruiterId) {
        TypedQuery<Need> query = entityManager.createQuery(
                        FINDALL_BY_RECRUITER, Need.class)
                .setParameter("recruiterId", recruiterId);

        return query.getResultList();
    }

    @Override
    public List<Need> getAll() {
        TypedQuery<Need> query = entityManager.createQuery(NEED_GETALL, Need.class);

        return query.getResultList();
    }

    @Override
    public List<Need> searchWithString(String research) {
        TypedQuery<Need> query = entityManager.createQuery(
                        NEED_SEARCHWITH_STRING, Need.class)
                .setParameter("search", "%" + research + "%");

        return query.getResultList();
    }
}
