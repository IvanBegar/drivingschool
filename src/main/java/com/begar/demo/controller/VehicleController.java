package com.begar.demo.controller;

import com.begar.demo.entity.Vehicle;
import com.begar.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable int id) {
        return vehicleService.getVehicle(id);
    }

    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
    }

    @PutMapping
    public void updateVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.updateVehicle(vehicle);
    }

    @PatchMapping("/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Vehicle vehicle = vehicleService.getVehicle(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Vehicle.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, vehicle, v);
        });
        vehicleService.updateVehicle(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
    }
}


