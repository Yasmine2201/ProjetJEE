package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ApplicationUser {
    public enum Role {Admin, Teacher, Recruiter}

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userId", nullable = false)
    private int userId;
    @Basic
    @Column(name = "login", nullable = false, length = 50, unique = true)
    private String login;
    @Basic
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "mail", nullable = false, length = 100)
    private String mail;
    @Basic
    @Column(name = "webSite", nullable = true, length = 100)
    private String webSite;
    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUser that = (ApplicationUser) o;
        return userId == that.userId && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(name, that.name) && Objects.equals(mail, that.mail) && Objects.equals(webSite, that.webSite) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, role, name, mail, webSite, phone);
    }
}
