package com.begar.demo.controller;

import com.begar.demo.dto.IncomeForPeriodDTO;
import com.begar.demo.dto.IncomePerCategoryDTO;
import com.begar.demo.dto.PaymentForPeriodDTO;
import com.begar.demo.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @RequestMapping("/income-for-period/{str}_{end}")
    public IncomeForPeriodDTO getIncomeForPeriod(@PathVariable String str, @PathVariable String end) {
        return incomeService.getIncomeForPeriod(str,end);
    }

    @RequestMapping("/payment-for-period/{str}_{end}")
    public PaymentForPeriodDTO getPaymentForPeriod(@PathVariable String str, @PathVariable String end) {
        return incomeService.getPaymentForPeriod(str,end);
    }
}
