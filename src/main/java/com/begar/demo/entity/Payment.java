package com.begar.demo.entity;

public class Payment {

    private int payment_id;
    private Student student;
    private String date;
    private double size;
    private String comment;

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + payment_id +
                ", idStudent=" + student +
                ", dateOfPayment='" + date + '\'' +
                ", paymentSize=" + size +
                ", paymentComment='" + comment + '\'' +
                '}';
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
