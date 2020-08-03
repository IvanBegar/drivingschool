package com.begar.demo.entity;

public class VehiclePayment {

    private int idVehiclePayment;
    private int idVehicle;
    private double size;
    private String date;
    private String paymentComment;

    @Override
    public String toString() {
        return "VehiclePayment{" +
                "idVehiclePayment=" + idVehiclePayment +
                ", idVehicle=" + idVehicle +
                ", size=" + size +
                ", date='" + date + '\'' +
                ", paymentCommnet='" + paymentComment + '\'' +
                '}';
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getPaymentComment() {
        return paymentComment;
    }

    public void setPaymentComment(String paymentComment) {
        this.paymentComment = paymentComment;
    }

    public int getIdVehiclePayment() {
        return idVehiclePayment;
    }

    public void setIdVehiclePayment(int idVehiclePayment) {
        this.idVehiclePayment = idVehiclePayment;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
