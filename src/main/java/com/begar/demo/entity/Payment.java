package com.begar.demo.entity;

import com.begar.demo.dto.StudentDTO;

public class Payment {

    private int idPayment;
    private StudentDTO student;
    private String dateOfPayment;
    private double paymentSize;
    private String paymentComment;

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", idStudent=" + student +
                ", dateOfPayment='" + dateOfPayment + '\'' +
                ", paymentSize=" + paymentSize +
                ", paymentComment='" + paymentComment + '\'' +
                '}';
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public double getPaymentSize() {
        return paymentSize;
    }

    public void setPaymentSize(double paymentSize) {
        this.paymentSize = paymentSize;
    }

    public String getPaymentComment() {
        return paymentComment;
    }

    public void setPaymentComment(String paymentComment) {
        this.paymentComment = paymentComment;
    }
}
