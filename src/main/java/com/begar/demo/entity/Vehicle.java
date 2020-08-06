package com.begar.demo.entity;

import com.begar.demo.dto.GroupDTO;
import com.begar.demo.dto.GroupForVehicleDTO;
import java.util.List;

public class Vehicle {

    private int vehicle_id;
    private String autoBrand;
    private String govNumber;
    private String year;
    private List<GroupDTO> groups;

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

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public String getGovNumber() {
        return govNumber;
    }

    public void setGovNumber(String govNumber) {
        this.govNumber = govNumber;
    }

    @Override
    public String
    toString() {
        return "Vehicle{" +
                "idVehicle=" + vehicle_id +
                ", autoBrand='" + autoBrand + '\'' +
                ", govNumber='" + govNumber + '\'' +
                ", year='" + year + '\'' +
                ", groups=" + groups +
                '}';
    }
}
