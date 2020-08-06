package com.begar.demo.entity;

public class UtilityPayment {

    private int utility_payment_id;
    private double size;
    private String date;

    @Override
    public String toString() {
        return "UtilityPayment{" +
                "idUtilityPayment=" + utility_payment_id +
                ", size=" + size +
                ", date='" + date + '\'' +
                '}';
    }

    public int getUtility_payment_id() {
        return utility_payment_id;
    }

    public void setUtility_payment_id(int utility_payment_id) {
        this.utility_payment_id = utility_payment_id;
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
