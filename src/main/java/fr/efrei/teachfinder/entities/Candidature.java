package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidature", schema = "teach_finder_db", indexes = {
        @Index(name = "needId", columnList = "needId"),
        @Index(name = "schoolName", columnList = "schoolName")
})
public class Candidature {
    @EmbeddedId
    private CandidatureId id;

    @MapsId("teacherId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacherId", nullable = false)
    private Teacher teacher;

    @MapsId("needId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "needId", nullable = false)
    private Need need;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schoolName", nullable = false)
    private School schoolName;

    @NotNull
    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn;

    @NotNull
    @Column(name = "isInitiatedByTeacher", nullable = false)
    private Boolean isInitiatedByTeacher = false;

    @NotNull
    @Column(name = "isValidatedByTeacher", nullable = false)
    private Boolean isValidatedByTeacher = false;

    @NotNull
    @Column(name = "isValidatedByRecruiter", nullable = false)
    private Boolean isValidatedByRecruiter = false;

    @NotNull
    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;

    public CandidatureId getId() {
        return id;
    }

    public void setId(CandidatureId id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public School getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(School schoolName) {
        this.schoolName = schoolName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getIsInitiatedByTeacher() {
        return isInitiatedByTeacher;
    }

    public void setIsInitiatedByTeacher(Boolean isInitiatedByTeacher) {
        this.isInitiatedByTeacher = isInitiatedByTeacher;
    }

    public Boolean getIsValidatedByTeacher() {
        return isValidatedByTeacher;
    }

    public void setIsValidatedByTeacher(Boolean isValidatedByTeacher) {
        this.isValidatedByTeacher = isValidatedByTeacher;
    }

    public Boolean getIsValidatedByRecruiter() {
        return isValidatedByRecruiter;
    }

    public void setIsValidatedByRecruiter(Boolean isValidatedByRecruiter) {
        this.isValidatedByRecruiter = isValidatedByRecruiter;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

}