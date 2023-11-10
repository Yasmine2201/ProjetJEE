package fr.efrei.teachfinder.beans;

public class TeacherBean {

    private Integer teacherId;
    private String experiences;
    private String skills;
    private String personnalInterests;

    private String schoolInterests;
    private String desiredLevels;
    private String contractType;
    private String academicCertifications;
    private String otherInformations;
    private String recommendations;

    public Integer getTeacherId() {
        return teacherId;
    }

    public String getSchoolInterests(){return schoolInterests;}

    public void setSchoolInterests(String schoolInterests) {
        this.schoolInterests = schoolInterests;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public String getDesiredLevels() {
        return desiredLevels;
    }

    public void setDesiredLevels(String desiredLevels) {
        this.desiredLevels = desiredLevels;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
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

    public void setRecommendations(String references) {
        this.recommendations = references;
    }
}
