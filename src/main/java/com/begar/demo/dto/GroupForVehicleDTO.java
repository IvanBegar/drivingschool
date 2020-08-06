package com.begar.demo.dto;

public class GroupForVehicleDTO {

    private String groupName;
    private String categoryName;
    private String schedule;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getScheduleDescription() {
        return schedule;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.schedule = scheduleDescription;
    }
}
