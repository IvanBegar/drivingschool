package com.begar.demo.entity;

public class Payment {

    private int idPayment;
    private int idStudent;
    private String dateOfPayment;
    private double paymentSize;
    private String paymentComment;

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", idStudent=" + idStudent +
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

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
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
