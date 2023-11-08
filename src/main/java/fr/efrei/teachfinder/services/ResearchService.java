package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.ISchoolDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.entities.Teacher;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ResearchService {

    @Inject
    ISchoolDAO schoolDAO;
    @Inject
    ITeacherDAO teacherDAO;

    public List<School> researchSchool(String searchText) {
        return schoolDAO.searchWithString(searchText);
    }

    public List<Teacher> researchSkills(String searchText) {
        return teacherDAO.searchWithSkills(searchText);
    }
}
