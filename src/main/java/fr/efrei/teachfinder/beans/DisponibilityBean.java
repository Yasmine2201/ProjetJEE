package fr.efrei.teachfinder.beans;

public class DisponibilityBean {

    private Integer disponibilityId;
    private Integer teacherId;
    private String startDate;
    private String endDate;

    public DisponibilityBean() { }

    public Integer getDisponibilityId() {
        return disponibilityId;
    }

    public void setDisponibilityId(Integer disponibilityId) {
        this.disponibilityId = disponibilityId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
