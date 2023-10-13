package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(fr.efrei.teachfinder.entities.RefsPK.class)
public class Refs {
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

    @Basic
    @Column(name = "rating", nullable = true, length = 50)
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Refs refs = (Refs) o;
        return teacherId == refs.teacherId && schoolId == refs.schoolId && Objects.equals(rating, refs.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, schoolId, rating);
    }
}
