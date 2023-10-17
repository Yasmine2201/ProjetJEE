package fr.efrei.teachfinder.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EvaluationId implements Serializable {
    @Serial
    private static final long serialVersionUID = -6623786698583336970L;
    @NotNull
    @Column(name = "teacherId", nullable = false)
    private Integer teacherId;

    @Size(max = 128)
    @NotNull
    @Column(name = "schoolName", nullable = false, length = 128)
    private String schoolName;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationId entity = (EvaluationId) o;
        return Objects.equals(this.teacherId, entity.teacherId) &&
                Objects.equals(this.schoolName, entity.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, schoolName);
    }

}