package com.begar.demo.service;

import com.begar.demo.entity.Payment;
import com.begar.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.getPayments();
    }

    public Payment getPayment(int id) {
        return paymentRepository.getPayment(id);
    }

    public void addPayment(Payment payment) {
        paymentRepository.addPayment(payment);
    }

    public String updatePayment(Payment payment) {
        String result = "";
        if (paymentRepository.getPayment(payment.getIdPayment()).getIdPayment() == 0) {
            result = "Payment don`t exist!";
        } else {
            paymentRepository.updatePayment(payment);
            result = "Payment successfully updated";
        }
        return result;
    }

    public void deletePayment(int id) {
        paymentRepository.deletePayment(id);
    }
}
