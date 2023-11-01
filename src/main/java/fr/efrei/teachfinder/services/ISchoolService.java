package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.Need;
import fr.efrei.teachfinder.entities.Recruiter;
import fr.efrei.teachfinder.entities.School;

import java.util.List;

public interface ISchoolService {

    School getSchool(String schoolName);

    List<Need> getSchoolRunningNeeds(String schoolName);

    List<Recruiter> getSchoolRecruiters(String schoolName);

    School updateSchool(School school);
}
