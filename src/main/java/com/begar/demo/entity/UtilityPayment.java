package com.begar.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "utility_payment", schema = "hibernate_db")
public class UtilityPayment {

    @Id
    @Column(name = "utility_payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int utility_payment_id;

    @Column(name = "size")
    private double size;

    @Column(name = "date")
    private String date;

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

    @Override
    public String toString() {
        return "UtilityPayment{" +
                "utility_payment_id=" + utility_payment_id +
                ", size=" + size +
                ", date='" + date + '\'' +
                '}';
    }
}
