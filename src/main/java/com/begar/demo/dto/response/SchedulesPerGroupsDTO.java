package com.begar.demo.dto.response;

public class SchedulesPerGroupsDTO {

    private String groupName;
    private String scheduleName;
    private String description;

    public SchedulesPerGroupsDTO(String groupName, String scheduleName, String description) {
        this.groupName = groupName;
        this.scheduleName = scheduleName;
        this.description = description;
    }

    public SchedulesPerGroupsDTO() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
