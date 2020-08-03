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

    @PostMapping("/vehicle-payments")
    public void addUtilityPayment(@RequestBody VehiclePayment vehiclePayment) {
        vehiclePaymentService.addVehiclePayment(vehiclePayment);
    }

    @PutMapping("/vehicle-payments")
    public String updateVehiclePayment(@RequestBody VehiclePayment vehiclePayment) {
        return vehiclePaymentService.updateVehiclePayment(vehiclePayment);
    }

    @PatchMapping("/vehicle-payments/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        VehiclePayment vehiclePayment = vehiclePaymentService.getVehiclePayment(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(VehiclePayment.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, vehiclePayment, v);
        });
        vehiclePaymentService.updateVehiclePayment(vehiclePayment);
    }

    @DeleteMapping("/vehicle-payments/{id}")
    public void deleteVehiclePayment(@PathVariable int id) {
        vehiclePaymentService.deleteVehiclePayment(id);
    }
}
