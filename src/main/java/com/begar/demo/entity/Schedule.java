package com.begar.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "schedule", schema = "hibernate_db")
public class Schedule {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schedule_id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

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

    @Override
    public String toString() {
        return "Schedule{" +
                "schedule_id=" + schedule_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
