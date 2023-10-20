package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Recruiter {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "recruiterId")
    private int recruiterId;
    @Basic
    @Column(name = "recruitingMethod")
    private String recruitingMethod;
    @Basic
    @Column(name = "recruitingTools")
    private String recruitingTools;
    @Basic
    @Column(name = "schoolId")
    private Integer schoolId;

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getRecruitingMethod() {
        return recruitingMethod;
    }

    public void setRecruitingMethod(String recruitingMethod) {
        this.recruitingMethod = recruitingMethod;
    }

    public String getRecruitingTools() {
        return recruitingTools;
    }

    public void setRecruitingTools(String recruitingTools) {
        this.recruitingTools = recruitingTools;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recruiter recruiter = (Recruiter) o;
        return recruiterId == recruiter.recruiterId && Objects.equals(recruitingMethod, recruiter.recruitingMethod) && Objects.equals(recruitingTools, recruiter.recruitingTools) && Objects.equals(schoolId, recruiter.schoolId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recruiterId, recruitingMethod, recruitingTools, schoolId);
    }
}
