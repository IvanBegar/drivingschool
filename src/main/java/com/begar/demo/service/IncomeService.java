package com.begar.demo.service;

import com.begar.demo.dto.IncomeForPeriodDTO;
import com.begar.demo.dto.IncomePerCategoryDTO;
import com.begar.demo.dto.PaymentForPeriodDTO;
import com.begar.demo.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public IncomeForPeriodDTO getIncomeForPeriod(String str, String end) {
       return incomeRepository.getIncomeForPeriod(str, end);
    }

    public PaymentForPeriodDTO getPaymentForPeriod(String str, String end) {
        return incomeRepository.getPaymentForPeriod(str, end);
    }
}
