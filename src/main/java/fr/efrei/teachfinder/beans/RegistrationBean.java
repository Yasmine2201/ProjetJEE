package fr.efrei.teachfinder.beans;

import fr.efrei.teachfinder.entities.Registration;

import java.io.Serializable;

public class RegistrationBean implements Serializable {

    private Integer registrationId;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String role;
    private String email;
    private String phone;
    private String status;
    private String schoolName;

    public RegistrationBean() {

    }

    public RegistrationBean(Registration registration) {
        registrationId = registration.getRegistrationId();
        login = registration.getLogin();
        firstname = registration.getFirstname();
        lastname = registration.getLastname();
        role = registration.getRole().name();
        schoolName = registration.getSchoolName().getSchoolName();
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
