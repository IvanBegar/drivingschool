package com.begar.demo.dto.response;

public class PaymentForPeriodDTO {

    private double paymentForPeriod;

    public PaymentForPeriodDTO(double paymentForPeriod) {
        this.paymentForPeriod = paymentForPeriod;
    }

    public PaymentForPeriodDTO() {
    }

    public double getPaymentForPeriod() {
        return paymentForPeriod;
    }

    public void setPaymentForPeriod(double paymentForPeriod) {
        this.paymentForPeriod = paymentForPeriod;
    }
}
