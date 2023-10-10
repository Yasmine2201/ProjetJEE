package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Registration {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "login", nullable = false, length = 50)
    private String login;
    @Basic
    @Column(name = "password", nullable = false, length = 256)
    private String password;
    @Basic
    @Column(name = "role", nullable = false)
    private Object role;
    @Basic
    @Column(name = "status", nullable = false)
    private Object status;

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

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, status);
    }
}
