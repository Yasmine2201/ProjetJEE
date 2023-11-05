package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "teacher", schema = "teach_finder_db")
public class Teacher {
    @Id
    @Column(name = "teacherId", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacherId", nullable = false)
    private ApplicationUser applicationuser;

    @Lob
    @Column(name = "experiences")
    private String experiences;

    @Size(max = 256)
    @Column(name = "skills", length = 256)
    private String skills;

    @Size(max = 256)
    @Column(name = "personnalInterests", length = 256)
    private String personnalInterests;

    @Size(max = 256)
    @Column(name = "schoolInterests", length = 256)
    private String schoolInterests;

    @Size(max = 64)
    @Column(name = "desiredLevels", length = 64)
    private String desiredLevels;

    @Lob
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contractType", nullable = false)
    private ContractType contractType = ContractType.Any;

    @Size(max = 256)
    @Column(name = "academicCertifications", length = 256)
    private String academicCertifications;

    @Lob
    @Column(name = "otherInformations")
    private String otherInformations;

    @Size(max = 256)
    @Column(name = "recommendations", length = 256)
    private String recommendations;

    @OneToMany(mappedBy = "teacher")
    private Set<Candidature> candidatures = new LinkedHashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Disponibility> disponibilities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Evaluation> evaluations = new LinkedHashSet<>();

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Set<Disponibility> getDisponibilities() {
        return disponibilities;
    }

    public void setDisponibilities(Set<Disponibility> disponibilities) {
        this.disponibilities = disponibilities;
    }

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

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPersonnalInterests() {
        return personnalInterests;
    }

    public void setPersonnalInterests(String personnalInterests) {
        this.personnalInterests = personnalInterests;
    }

    public String getSchoolInterests() {
        return schoolInterests;
    }

    public void setSchoolInterests(String schoolInterests) {
        this.schoolInterests = schoolInterests;
    }

    public String getDesiredLevels() {
        return desiredLevels;
    }

    public void setDesiredLevels(String desiredLevels) {
        this.desiredLevels = desiredLevels;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getAcademicCertifications() {
        return academicCertifications;
    }

    public void setAcademicCertifications(String academicCertifications) {
        this.academicCertifications = academicCertifications;
    }

    public String getOtherInformations() {
        return otherInformations;
    }

    public void setOtherInformations(String otherInformations) {
        this.otherInformations = otherInformations;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

}