package com.begar.demo.entity;

import com.begar.demo.dto.CategoryForGroupDTO;
import com.begar.demo.dto.TeacherForGroupDTO;
import com.begar.demo.dto.VehicleForGroupDTO;
import java.util.List;

public class Group {

    private int idGroup;
    private CategoryForGroupDTO category;
    private Schedule schedule;
    private String groupNumber;
    private String startDate;
    private String endDate;
    private List<TeacherForGroupDTO> teachers;
    private List<VehicleForGroupDTO> vehicles;

    @Override
    public String toString() {
        return "Group{" +
                "idGroup=" + idGroup +
                ", idCategory=" + category +
                ", idSchedule=" + schedule +
                ", groupNumber=" + groupNumber +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public CategoryForGroupDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryForGroupDTO category) {
        this.category = category;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
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

    public List<TeacherForGroupDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherForGroupDTO> teachers) {
        this.teachers = teachers;
    }

    public List<VehicleForGroupDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleForGroupDTO> vehicles) {
        this.vehicles = vehicles;
    }
}
