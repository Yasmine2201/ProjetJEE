package fr.efrei.teachfinder.rest;

import fr.efrei.teachfinder.entities.School;
import fr.efrei.teachfinder.services.SchoolService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("schools")
public class SchoolsResource {
    @EJB
    private SchoolService schoolService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<School> getSchools() {
        return schoolService
            .getAllSchools()
            .stream()
            .map(school -> {
                School s = new School();
                s.setSchoolName(school.getSchoolName());
                return s;
            }).toList();
    }
}
