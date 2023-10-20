package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Need {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "needId", nullable = false)
    private int needId;
    @Basic
    @Column(name = "contractType", nullable = true, length = 50)
    private String contractType;
    @Basic
    @Column(name = "subject", nullable = true, length = 100)
    private String subject;
    @Basic
    @Column(name = "teachingMethod", nullable = true, length = 100)
    private String teachingMethod;
    @Basic
    @Column(name = "schoolId", nullable = false)
    private int schoolId;
    @Basic
    @Column(name = "recruiterId", nullable = true)
    private Integer recruiterId;
    @Basic
    @Column(name = "workingTime", nullable = true)
    private Integer workingTime;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    public int getNeedId() {
        return needId;
    }

    public void setNeedId(int needId) {
        this.needId = needId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Integer recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Need need = (Need) o;
        return needId == need.needId && schoolId == need.schoolId && Objects.equals(contractType, need.contractType) && Objects.equals(subject, need.subject) && Objects.equals(teachingMethod, need.teachingMethod) && Objects.equals(recruiterId, need.recruiterId) && Objects.equals(workingTime, need.workingTime) && Objects.equals(description, need.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(needId, contractType, subject, teachingMethod, schoolId, recruiterId, workingTime, description);
    }
}
