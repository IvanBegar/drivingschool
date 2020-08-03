package com.begar.demo.controller;

import com.begar.demo.entity.UtilityPayment;
import com.begar.demo.service.UtilityPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class UtilityPaymentController {

    @Autowired
    private UtilityPaymentService utilityPaymentService;

    @RequestMapping("/utility-payments")
    public List<UtilityPayment> getUtilityPayments() {
        return utilityPaymentService.getUtilityPayments();
    }

    @GetMapping("/utility-payments/{id}")
    public UtilityPayment getUtilityPayment(@PathVariable int id) {
        return utilityPaymentService.getUtilityPayment(id);
    }

    @PostMapping("/utility-payments")
    public void addUtilityPayment(@RequestBody UtilityPayment utilityPayment) {
        utilityPaymentService.addUtilityPayment(utilityPayment);
    }

    @PutMapping("/utility-payments")
    public String updateUtilityPayment(@RequestBody UtilityPayment utilityPayment) {
        return utilityPaymentService.updateUtilityPayment(utilityPayment);
    }

    @PatchMapping("/utility-payments/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        UtilityPayment utilityPayment = utilityPaymentService.getUtilityPayment(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(UtilityPayment.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, utilityPayment, v);
        });
        utilityPaymentService.updateUtilityPayment(utilityPayment);
    }

    @DeleteMapping("/utility-payments/{id}")
    public void deleteUtilityPayment(@PathVariable int id) {
        utilityPaymentService.deleteUtilityPayment(id);
    }
}
