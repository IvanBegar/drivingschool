package com.begar.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle", schema = "hibernate_db")
public class Vehicle {

    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicle_id;
    @Column(name = "autoBrand")
    private String autoBrand;
    @Column(name = "govNumber")
    private String govNumber;
    @Column(name = "year")
    private String year;
    @JsonManagedReference
    @ManyToMany(mappedBy = "vehicles")
    private List<Group> groups;

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getAutoBrand() {
        return autoBrand;
    }

    public void setAutoBrand(String autoBrand) {
        this.autoBrand = autoBrand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getGovNumber() {
        return govNumber;
    }

    public void setGovNumber(String govNumber) {
        this.govNumber = govNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", autoBrand='" + autoBrand + '\'' +
                ", govNumber='" + govNumber + '\'' +
                ", year='" + year + '\'' +
                ", groups=" + groups +
                '}';
    }
}
