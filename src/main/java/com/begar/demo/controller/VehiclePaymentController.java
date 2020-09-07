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
@RequestMapping("/vehicle-payments")
@CrossOrigin
public class VehiclePaymentController {

    @Autowired
    private VehiclePaymentService vehiclePaymentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<VehiclePayment> getVehiclePayments() {
        return vehiclePaymentService.getVehiclePayments();
    }

    @GetMapping("/{id}")
    public VehiclePayment getUtilityPayment(@PathVariable int id) {
        return vehiclePaymentService.getVehiclePayment(id);
    }

    @PostMapping("/vehicle-id={id}")
    public void addUtilityPayment(@RequestBody VehiclePayment vehiclePayment, @PathVariable int id) {
        vehiclePaymentService.addVehiclePayment(vehiclePayment, id);
    }

    @PutMapping("/vehicle-id={id}")
    public void updateVehiclePayment(@RequestBody VehiclePayment vehiclePayment, @PathVariable int id) {
        vehiclePaymentService.updateVehiclePayment(vehiclePayment);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        VehiclePayment vehiclePayment = vehiclePaymentService.getVehiclePayment(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(VehiclePayment.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, vehiclePayment, v);
        });
        vehiclePaymentService.updateVehiclePayment(vehiclePayment);
    }

    @DeleteMapping("/{id}")
    public void deleteVehiclePayment(@PathVariable int id) {
        vehiclePaymentService.deleteVehiclePayment(id);
    }
}
