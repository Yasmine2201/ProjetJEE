package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(fr.efrei.teachfinder.entities.CandidaturePK.class)
public class Candidature {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "teacherId", nullable = false)
    private int teacherId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "needId", nullable = false)
    private int needId;

    public int getNeedId() {
        return needId;
    }

    public void setNeedId(int needId) {
        this.needId = needId;
    }

    @Basic
    @Column(name = "desiredLevel", nullable = true, length = 100)
    private String desiredLevel;

    public String getDesiredLevel() {
        return desiredLevel;
    }

    public void setDesiredLevel(String desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    @Basic
    @Column(name = "status", nullable = false)
    private Object status;

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    @Basic
    @Column(name = "isValidatedByTeacher", nullable = true)
    private Byte isValidatedByTeacher;

    public Byte getIsValidatedByTeacher() {
        return isValidatedByTeacher;
    }

    public void setIsValidatedByTeacher(Byte isValidatedByTeacher) {
        this.isValidatedByTeacher = isValidatedByTeacher;
    }

    @Basic
    @Column(name = "isValidatedByRecruiter", nullable = true)
    private Byte isValidatedByRecruiter;

    public Byte getIsValidatedByRecruiter() {
        return isValidatedByRecruiter;
    }

    public void setIsValidatedByRecruiter(Byte isValidatedByRecruiter) {
        this.isValidatedByRecruiter = isValidatedByRecruiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidature that = (Candidature) o;
        return teacherId == that.teacherId && needId == that.needId && Objects.equals(desiredLevel, that.desiredLevel) && Objects.equals(status, that.status) && Objects.equals(isValidatedByTeacher, that.isValidatedByTeacher) && Objects.equals(isValidatedByRecruiter, that.isValidatedByRecruiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, needId, desiredLevel, status, isValidatedByTeacher, isValidatedByRecruiter);
    }
}
