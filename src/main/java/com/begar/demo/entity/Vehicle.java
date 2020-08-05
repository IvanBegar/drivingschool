package com.begar.demo.entity;

import com.begar.demo.dto.GroupForVehicleDTO;
import java.util.List;

public class Vehicle {

    private int idVehicle;
    private String autoBrand;
    private String govNumber;
    private String year;
    private List<GroupForVehicleDTO> groups;

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
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

    public List<GroupForVehicleDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupForVehicleDTO> groups) {
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
                "idVehicle=" + idVehicle +
                ", autoBrand='" + autoBrand + '\'' +
                ", govNumber='" + govNumber + '\'' +
                ", year='" + year + '\'' +
                ", groups=" + groups +
                '}';
    }
}
