package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "recruiter", schema = "teach_finder_db", indexes = {
        @Index(name = "schoolName", columnList = "schoolName")
})
public class Recruiter {
    @Id
    @Column(name = "recruiterId", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruiterId", nullable = false)
    private ApplicationUser applicationuser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schoolName", nullable = false)
    private School schoolName;

    @OneToMany(mappedBy = "recruiter")
    private Set<Candidature> candidatures = new LinkedHashSet<>();

    public Set<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(Set<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ApplicationUser getApplicationuser() {
        return applicationuser;
    }

    public void setApplicationuser(ApplicationUser applicationuser) {
        this.applicationuser = applicationuser;
    }

    public School getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(School schoolName) {
        this.schoolName = schoolName;
    }

}