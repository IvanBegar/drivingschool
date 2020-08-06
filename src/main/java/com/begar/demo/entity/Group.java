package com.begar.demo.entity;

import com.begar.demo.dto.TeacherForGroupDTO;
import com.begar.demo.dto.VehicleDTO;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private int group_id;
    private Category category;
    private Schedule schedule;
    private String groupName;
    private String startDate;
    private String endDate;
    private List<TeacherForGroupDTO> teachers;
    private List<VehicleDTO> vehicles;
    private List<Link> links = new ArrayList<>();

    @Override
    public String toString() {
        return "Group{" +
                "idGroup=" + group_id +
                ", idCategory=" + category +
                ", idSchedule=" + schedule +
                ", groupNumber=" + groupName +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link();
        link.setHref(url);
        link.setRel(rel);
        links.add(link);
    }
}
