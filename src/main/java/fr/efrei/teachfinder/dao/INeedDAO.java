package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Need;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface INeedDAO {

    Need findById(int needId);

    Need create(Need need) throws EntityExistsException;

    Need update(Need need) throws EntityNotFoundException;

    List<Need> findAllBySchool(String schoolName);

    List<Need> findAllByRecruiter(int recruiterId);

    List<Need> getAll();

    /**
     * @param research : schoolName or subject
     * @return corresponding Needs
     */
    List<Need> searchWithString(String research);
}
