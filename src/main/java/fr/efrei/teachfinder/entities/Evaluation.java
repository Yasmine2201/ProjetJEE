package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "evaluation", schema = "teach_finder_db", indexes = {
        @Index(name = "schoolName", columnList = "schoolName")
})
public class Evaluation {
    @EmbeddedId
    private EvaluationId id;

    @MapsId("teacherId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacherId", nullable = false)
    private Teacher teacher;

    @MapsId("schoolName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schoolName", nullable = false)
    private School schoolName;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Lob
    @Column(name = "comment")
    private String comment;

    public EvaluationId getId() {
        return id;
    }

    public void setId(EvaluationId id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public School getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(School schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}