package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "registration", schema = "teach_finder_db", indexes = {
        @Index(name = "schoolName", columnList = "schoolName")
})
public class Registration {
    @Size(max = 32)
    @NotNull
    @Column(name = "login", nullable = false, length = 32)
    private String login;

    @Size(max = 64)
    @NotNull
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Size(max = 32)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 32)
    private String firstname;

    @Size(max = 32)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 32)
    private String lastname;

    @Size(max = 64)
    @NotNull
    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Size(max = 16)
    @Column(name = "phone", length = 16)
    private String phone;

    @NotNull
    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType role;

    @NotNull
    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolName")
    private School schoolName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registrationId", nullable = false)
    private Integer registrationId;

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public School getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(School schoolName) {
        this.schoolName = schoolName;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}