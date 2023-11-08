package fr.efrei.teachfinder.rest;

import fr.efrei.teachfinder.beans.RegistrationBean;
import fr.efrei.teachfinder.exceptions.IncompleteEntityException;
import fr.efrei.teachfinder.exceptions.UnavailableLoginException;
import fr.efrei.teachfinder.services.RegistrationService;
import fr.efrei.teachfinder.utils.Constants;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/register")
public class RegistrationResource {

    @EJB
    RegistrationService registrationService;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            @FormParam("action") String action,
            @FormParam("login") String login,
            @FormParam("password") String password,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("email") String email,
            @DefaultValue("") @FormParam("phone") String phone,
            @FormParam("role") String role,
            @DefaultValue("") @FormParam("schoolName") String schoolName
    )
    {
        RegistrationBean registration = new RegistrationBean();
        registration.setLogin(login);
        registration.setPassword(password);
        registration.setFirstname(firstname);
        registration.setLastname(lastname);
        registration.setEmail(email);
        registration.setPhone(phone);
        registration.setRole(role);
        registration.setSchoolName(schoolName);

        Response.Status responseStatus;
        String responseMessage;

        try {
            registrationService.createRegistration(registration);
            responseStatus = Response.Status.CREATED;
            responseMessage = Constants.Messages.SUCCESSFUL_REGISTRATION;
        } catch (IncompleteEntityException e) {
            responseStatus = Response.Status.BAD_REQUEST;
            responseMessage = Constants.Messages.MISSING_FIELD;
        } catch (UnavailableLoginException e) {
            responseStatus = Response.Status.BAD_REQUEST;
            responseMessage = Constants.Messages.UNAVAILABLE_LOGIN;
        } catch (IllegalArgumentException e) {
            responseStatus = Response.Status.BAD_REQUEST;
            responseMessage = Constants.Messages.INVALID_ARGUMENT;
        }

        return Response.status(responseStatus).entity(responseMessage).build();
    }
}