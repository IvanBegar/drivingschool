package com.begar.demo.controller;

import com.begar.demo.entity.VehiclePayment;
import com.begar.demo.service.VehiclePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class VehiclePaymentController {

    @Autowired
    private VehiclePaymentService vehiclePaymentService;

    @RequestMapping("/vehicle-payments")
    public List<VehiclePayment> getVehiclePayments() {
        return vehiclePaymentService.getVehiclePayments();
    }

    @GetMapping("/vehicle-payments/{id}")
    public VehiclePayment getUtilityPayment(@PathVariable int id) {
        return vehiclePaymentService.getVehiclePayment(id);
    }

    @PostMapping("/vehicle-payments/vehicle-id={id}")
    public void addUtilityPayment(@RequestBody VehiclePayment vehiclePayment, @PathVariable int id) {
        vehiclePaymentService.addVehiclePayment(vehiclePayment, id);
    }

    @PutMapping("/vehicle-payments/vehicle-id={id}")
    public String updateVehiclePayment(@RequestBody VehiclePayment vehiclePayment, @PathVariable int id) {
        return vehiclePaymentService.updateVehiclePayment(vehiclePayment, id);
    }

    @PatchMapping("/vehicle-payments/payment-id={id1}+vehicle-id={id2}")
    public void patchUpdate(@PathVariable int id1, @PathVariable int id2, @RequestBody Map<Object, Object> fields) {
        VehiclePayment vehiclePayment = vehiclePaymentService.getVehiclePayment(id1);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(VehiclePayment.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, vehiclePayment, v);
        });
        vehiclePaymentService.updateVehiclePayment(vehiclePayment, id2);
    }

    @DeleteMapping("/vehicle-payments/{id}")
    public void deleteVehiclePayment(@PathVariable int id) {
        vehiclePaymentService.deleteVehiclePayment(id);
    }
}
