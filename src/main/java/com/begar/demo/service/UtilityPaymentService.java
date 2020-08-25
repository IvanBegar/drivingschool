package com.begar.demo.service;

import com.begar.demo.entity.UtilityPayment;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.UtilityPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilityPaymentService {

    @Autowired
    private UtilityPaymentRepository utilityPaymentRepository;

    public List<UtilityPayment> getUtilityPayments() {
        return utilityPaymentRepository.getUtilityPayments();
    }

    public UtilityPayment getUtilityPayment(int id) {
        return utilityPaymentRepository.getUtilityPayment(id);
    }

    public void addUtilityPayment(UtilityPayment utilityPayment) {
        utilityPaymentRepository.addUtilityPayment(utilityPayment);
    }

    public void updateUtilityPayment(UtilityPayment utilityPayment) {
        if (utilityPaymentRepository.getUtilityPayment(utilityPayment.getUtility_payment_id()).getUtility_payment_id() == 0) {
            throw new DataException("Utility payment don`t exist!");
        } else {
            utilityPaymentRepository.updateUtilityPayment(utilityPayment);
        }
    }

    public void deleteUtilityPayment(int id) {
        utilityPaymentRepository.deleteUtilityPayment(id);
    }
}
