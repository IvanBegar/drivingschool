package com.begar.demo.service;

import com.begar.demo.entity.UtilityPayment;
import com.begar.demo.exception.NoDataException;
import com.begar.demo.repository.UtilityPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityPaymentService {

    @Autowired
    private UtilityPaymentRepository utilityPaymentRepository;

    public List<UtilityPayment> getUtilityPayments() {
        return utilityPaymentRepository.findAll();
    }

    public UtilityPayment getUtilityPayment(int id) {
        return utilityPaymentRepository.findById(id).orElseThrow(() -> new NoDataException("Utility Payment with id: " + id + " don't exist!"));
    }

    public void addUtilityPayment(UtilityPayment utilityPayment) {
        utilityPaymentRepository.save(utilityPayment);
    }

    public void updateUtilityPayment(UtilityPayment utilityPayment) {
        UtilityPayment existingUtilityPayment = utilityPaymentRepository.findById(utilityPayment.getUtility_payment_id())
                .orElseThrow(() -> new NoDataException("Utility Payment with id: " + utilityPayment.getUtility_payment_id() + " don't exist!"));
        existingUtilityPayment.setDate(utilityPayment.getDate());
        existingUtilityPayment.setSize(utilityPayment.getSize());
        utilityPaymentRepository.save(existingUtilityPayment);
    }

    public void deleteUtilityPayment(int id) {
        utilityPaymentRepository.deleteById(id);
    }
}
