package com.begar.demo.entity;

public class Category {

    private int idCategory;
    private String name;
    private double categoryPayment;
    private String studyTime;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCategoryPayment() {
        return categoryPayment;
    }

    public void setCategoryPayment(double categoryPayment) {
        this.categoryPayment = categoryPayment;
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
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", categoryPayment=" + categoryPayment +
                ", studeTime='" + studyTime + '\'' +
                '}';
    }
}
