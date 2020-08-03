package com.begar.demo.service;

import com.begar.demo.entity.UtilityPayment;
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

    public String updateUtilityPayment(UtilityPayment utilityPayment) {
        String result = "";
        if (utilityPaymentRepository.getUtilityPayment(utilityPayment.getIdUtilityPayment()).getIdUtilityPayment() == 0) {
            result = "UtilityPayment don`t exist!";
        } else {
            utilityPaymentRepository.updateUtilityPayment(utilityPayment);
            result = "UtilityPayment successfully updated";
        }
        return result;
    }

    public void deleteUtilityPayment(int id) {
        utilityPaymentRepository.deleteUtilityPayment(id);
    }
}