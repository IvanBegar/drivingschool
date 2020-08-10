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
@RequestMapping("/utility-payments")
public class UtilityPaymentController {

    @Autowired
    private UtilityPaymentService utilityPaymentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UtilityPayment> getUtilityPayments() {
        return utilityPaymentService.getUtilityPayments();
    }

    @GetMapping("/{id}")
    public UtilityPayment getUtilityPayment(@PathVariable int id) {
        return utilityPaymentService.getUtilityPayment(id);
    }

    @PostMapping
    public void addUtilityPayment(@RequestBody UtilityPayment utilityPayment) {
        utilityPaymentService.addUtilityPayment(utilityPayment);
    }

    @PutMapping
    public void updateUtilityPayment(@RequestBody UtilityPayment utilityPayment) {
        utilityPaymentService.updateUtilityPayment(utilityPayment);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        UtilityPayment utilityPayment = utilityPaymentService.getUtilityPayment(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(UtilityPayment.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, utilityPayment, v);
        });
        utilityPaymentService.updateUtilityPayment(utilityPayment);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilityPayment(@PathVariable int id) {
        utilityPaymentService.deleteUtilityPayment(id);
    }
}
