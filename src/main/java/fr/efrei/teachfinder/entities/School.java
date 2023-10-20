package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class School {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "schoolId")
    private int schoolId;
    @Basic
    @Column(name = "officialName")
    private String officialName;

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return schoolId == school.schoolId && Objects.equals(officialName, school.officialName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolId, officialName);
    }
}
