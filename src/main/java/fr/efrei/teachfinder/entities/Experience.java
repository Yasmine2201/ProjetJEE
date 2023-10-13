package fr.efrei.teachfinder.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.Objects;

@Entity
@jakarta.persistence.IdClass(fr.efrei.teachfinder.entities.ExperiencePK.class)
public class Experience {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "startingDate", nullable = false)
    private Date startingDate;

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "endingDate", nullable = false)
    private Date endingDate;

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

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
    @jakarta.persistence.Column(name = "schoolId", nullable = false)
    private int schoolId;

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return teacherId == that.teacherId && schoolId == that.schoolId && Objects.equals(startingDate, that.startingDate) && Objects.equals(endingDate, that.endingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingDate, endingDate, teacherId, schoolId);
    }
}
