package com.begar.demo.entity;

public class Schedule {

    private int schedule_id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + schedule_id +
                ", name='" + name + '\'' +
                ", scheduleDescription='" + description + '\'' +
                '}';
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
