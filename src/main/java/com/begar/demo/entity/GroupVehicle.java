package com.begar.demo.entity;

public class GroupVehicle {

    private int idGroupVehicle;
    private int idGroup;
    private int idVehicle;

    @Override
    public String toString() {
        return "GroupVehicle{" +
                "idGroupVehicle=" + idGroupVehicle +
                ", idGroup=" + idGroup +
                ", idVehicle=" + idVehicle +
                '}';
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public int getIdGroupVehicle() {
        return idGroupVehicle;
    }

    public void setIdGroupVehicle(int idGroupVehicle) {
        this.idGroupVehicle = idGroupVehicle;
    }
}
