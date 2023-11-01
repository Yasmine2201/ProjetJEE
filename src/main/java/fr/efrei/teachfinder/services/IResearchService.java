package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.entities.Teacher;

import java.util.List;

public interface IResearchService {

    List<School> researchSchool(String searchText);

    List<Teacher> researchSkills(String searchText);
}
