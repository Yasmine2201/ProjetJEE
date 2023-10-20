package fr.efrei.teachfinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "school", schema = "teach_finder_db")
public class School {
    @Id
    @Size(max = 128)
    @Column(name = "schoolName", nullable = false, length = 128)
    private String schoolName;

    @Size(max = 256)
    @Column(name = "address", length = 256)
    private String address;

    @Size(max = 256)
    @Column(name = "specializations", length = 256)
    private String specializations;

    @OneToMany(mappedBy = "schoolName")
    private Set<Need> needs = new LinkedHashSet<>();

    public Set<Need> getNeeds() {
        return needs;
    }

    public void setNeeds(Set<Need> needs) {
        this.needs = needs;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

}