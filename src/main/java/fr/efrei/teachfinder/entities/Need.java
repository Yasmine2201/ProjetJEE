package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "need", schema = "teach_finder_db", indexes = {
        @Index(name = "schoolName", columnList = "schoolName")
})
public class Need {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "needId", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schoolName", nullable = false)
    private School schoolName;

    @NotNull
    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "contractType", nullable = false)
    private ContractType contractType;

    @Size(max = 64)
    @NotNull
    @Column(name = "subject", nullable = false, length = 64)
    private String subject;

    @Lob
    @Column(name = "requirements")
    private String requirements;

    @Size(max = 32)
    @NotNull
    @Column(name = "timePeriod", nullable = false, length = 32)
    private String timePeriod;

    @Lob
    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "need")
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

    public School getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(School schoolName) {
        this.schoolName = schoolName;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}