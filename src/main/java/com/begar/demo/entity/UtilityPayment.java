package com.begar.demo.entity;

public class UtilityPayment {

    private int idUtilityPayment;
    private double size;
    private String date;

    @Override
    public String toString() {
        return "UtilityPayment{" +
                "idUtilityPayment=" + idUtilityPayment +
                ", size=" + size +
                ", date='" + date + '\'' +
                '}';
    }

    public int getIdUtilityPayment() {
        return idUtilityPayment;
    }

    public void setIdUtilityPayment(int idUtilityPayment) {
        this.idUtilityPayment = idUtilityPayment;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
