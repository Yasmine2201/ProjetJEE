package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Need;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class NeedDAO implements  INeedDAO {
    @Override
    public Need findById(int needId) {
        return null;
    }

    @Override
    public Need create(Need need) throws EntityExistsException {
        return null;
    }

    @Override
    public Need update(Need need) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<Need> findAllBySchool(String schoolName) {
        return null;
    }

    @Override
    public List<Need> findAllByRecruiter(int recruiterId) {
        return null;
    }

    @Override
    public List<Need> getAll() {
        return null;
    }

    @Override
    public List<Need> searchWithString(String research) {
        return null;
    }
}
