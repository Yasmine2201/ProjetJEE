package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.dao.INeedDAO;
import fr.efrei.teachfinder.dao.ISchoolDAO;
import fr.efrei.teachfinder.dao.ITeacherDAO;
import fr.efrei.teachfinder.entities.Need;
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
    @Inject
    INeedDAO needDAO;

    public List<School> researchSchool(String searchText) {
        return schoolDAO.searchWithString(searchText);
    }

    public List<Teacher> researchSkills(String searchText) {
        return teacherDAO.searchWithSkills(searchText);
    }

    public List<Need> researchNeed(String searchText) {
        return needDAO.searchWithString(searchText);
    }
}
