package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Candidature;
import fr.efrei.teachfinder.entities.Need;

public interface INeedService {

    Need getNeed(int needId);

    Need updateNeed(Need need);

    Candidature apply(int needId, int teacherId);
}
