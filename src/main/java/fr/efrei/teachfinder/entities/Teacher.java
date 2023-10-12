package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Teacher {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "teacherId")
    private int teacherId;
    @Basic
    @Column(name = "subjectExpertise")
    private String subjectExpertise;
    @Basic
    @Column(name = "skills")
    private String skills;
    @Basic
    @Column(name = "academicCertification")
    private String academicCertification;
    @Basic
    @Column(name = "otherInformations")
    private String otherInformations;
    @Basic
    @Column(name = "interestedInSchool")
    private Integer interestedInSchool;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectExpertise() {
        return subjectExpertise;
    }

    public void setSubjectExpertise(String subjectExpertise) {
        this.subjectExpertise = subjectExpertise;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAcademicCertification() {
        return academicCertification;
    }

    public void setAcademicCertification(String academicCertification) {
        this.academicCertification = academicCertification;
    }

    public String getOtherInformations() {
        return otherInformations;
    }

    public void setOtherInformations(String otherInformations) {
        this.otherInformations = otherInformations;
    }

    public Integer getInterestedInSchool() {
        return interestedInSchool;
    }

    public void setInterestedInSchool(Integer interestedInSchool) {
        this.interestedInSchool = interestedInSchool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId && Objects.equals(subjectExpertise, teacher.subjectExpertise) && Objects.equals(skills, teacher.skills) && Objects.equals(academicCertification, teacher.academicCertification) && Objects.equals(otherInformations, teacher.otherInformations) && Objects.equals(interestedInSchool, teacher.interestedInSchool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, subjectExpertise, skills, academicCertification, otherInformations, interestedInSchool);
    }
}
