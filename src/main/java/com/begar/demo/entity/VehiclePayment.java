package com.begar.demo.entity;

public class VehiclePayment {

    private int vehicle_payment_id;
    private Vehicle vehicle;
    private double size;
    private String date;
    private String comment;

    @Override
    public String toString() {
        return "VehiclePayment{" +
                "idVehiclePayment=" + vehicle_payment_id +
                ", idVehicle=" + vehicle +
                ", size=" + size +
                ", date='" + date + '\'' +
                ", paymentCommnet='" + comment + '\'' +
                '}';
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getVehicle_payment_id() {
        return vehicle_payment_id;
    }

    public void setVehicle_payment_id(int vehicle_payment_id) {
        this.vehicle_payment_id = vehicle_payment_id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
