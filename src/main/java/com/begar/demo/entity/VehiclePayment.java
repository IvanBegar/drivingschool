package com.begar.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_payment", schema = "hibernate_db")
public class VehiclePayment {

    @Id
    @Column(name = "vehicle_payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicle_payment_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "size")
    private double size;

    @Column(name = "date")
    private String date;

    @Column(name = "comment")
    private String comment;

    public VehiclePayment(int vehicle_payment_id, Vehicle vehicle, double size, String date, String comment) {
        this.vehicle_payment_id = vehicle_payment_id;
        this.vehicle = vehicle;
        this.size = size;
        this.date = date;
        this.comment = comment;
    }

    public VehiclePayment() {
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

    @Override
    public String toString() {
        return "VehiclePayment{" +
                "vehicle_payment_id=" + vehicle_payment_id +
                ", vehicle=" + vehicle +
                ", size=" + size +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
