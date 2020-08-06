package com.begar.demo.entity;

public class Category {

    private int category_id;
    private String name;
    private double payment;
    private String studyTime;

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
                "idCategory=" + category_id +
                ", name='" + name + '\'' +
                ", categoryPayment=" + payment +
                ", studeTime='" + studyTime + '\'' +
                '}';
    }
}
