package fr.efrei.teachfinder.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidatureId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1983502883307212913L;
    @NotNull
    @Column(name = "teacherId", nullable = false)
    private Integer teacherId;

    @NotNull
    @Column(name = "needId", nullable = false)
    private Integer needId;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getNeedId() {
        return needId;
    }

    public void setNeedId(Integer needId) {
        this.needId = needId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatureId entity = (CandidatureId) o;
        return Objects.equals(this.needId, entity.needId) &&
                Objects.equals(this.teacherId, entity.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(needId, teacherId);
    }

}