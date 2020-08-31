package com.begar.demo.service;

import com.begar.demo.entity.Payment;
import com.begar.demo.exception.DataException;
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

    public void addPayment(Payment payment, int id) {
        paymentRepository.addPayment(payment, id);
    }

    public void updatePayment(Payment payment) {
        if (paymentRepository.getPayment(payment.getPayment_id()).getPayment_id() == 0) {
            throw new DataException("Payment don`t exist!");
        } else {
            paymentRepository.updatePayment(payment);
        }
    }

    public void deletePayment(int id) {
        paymentRepository.deletePayment(id);
    }
}
