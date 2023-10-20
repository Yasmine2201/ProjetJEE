package fr.efrei.teachfinder.dao;

import fr.efrei.teachfinder.entities.Refs;

import java.util.List;

public interface IRefsDAO {

    List<Refs> findAllByTeacher(int teacherId );

    List<Refs> findAllBySchool(int schoolId );
}
