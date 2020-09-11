package com.begar.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "hibernate_db")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "name")
    private String name;

    @Column(name = "payment")
    private double payment;

    @Column(name = "studyTime")
    private String studyTime;

    public Category() {
    }

    public Category(int category_id, String name, double payment, String studyTime) {
        this.category_id = category_id;
        this.name = name;
        this.payment = payment;
        this.studyTime = studyTime;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                ", payment=" + payment +
                ", studyTime='" + studyTime + '\'' +
                '}';
    }
}
