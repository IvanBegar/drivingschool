package com.begar.demo.controller;

import com.begar.demo.entity.Payment;
import com.begar.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/payments")
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("/payments/{id}")
    public Payment getPayment(@PathVariable int id) {
        return paymentService.getPayment(id);
    }

    @PostMapping("/payments/student-id={id}")
    public void addPayment(@RequestBody Payment payment, @PathVariable int id) {
        paymentService.addPayment(payment, id);
    }

    @PutMapping("/payments/student-id={id}")
    public String updatePayment(@RequestBody Payment payment, @PathVariable int id) {
        return paymentService.updatePayment(payment, id);
    }

    @PatchMapping("/payments/student-id={id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Payment payment = paymentService.getPayment(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Payment.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, payment, v);
        });
        paymentService.updatePayment(payment, id);
    }

    @DeleteMapping("/payments/{id}")
    public void deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
    }
}
