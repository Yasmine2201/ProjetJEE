package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.SchoolDAO;
import fr.efrei.teachfinder.dao.TeacherDAO;
import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.entities.Teacher;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ResearchService {

    @Inject
    SchoolDAO schoolDAO;
    @Inject
    TeacherDAO teacherDAO;

    public List<School> researchSchool(String searchText) {
        return schoolDAO.searchWithString(searchText);
    }

    public List<Teacher> researchSkills(String searchText) {
        return teacherDAO.searchWithSkills(searchText);
    }
}
