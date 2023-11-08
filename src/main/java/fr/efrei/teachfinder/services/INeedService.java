package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;
import jakarta.persistence.EntityNotFoundException;

public interface INeedService {

    Need getNeed(int needId) throws EntityNotFoundException;

    Need updateNeed(Need need) throws EntityNotFoundException;

    Candidature apply(int needId, int teacherId);
}
