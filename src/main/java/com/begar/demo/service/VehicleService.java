package com.begar.demo.service;

import com.begar.demo.entity.Vehicle;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicle(int id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new DataException("Vehicle with id: " + id +" don't exist!"));
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicle.getVehicle_id()).orElseThrow(
                () -> new DataException("Vehicle with id: " + vehicle.getVehicle_id() +" don't exist!"));
        existingVehicle.setGovNumber(vehicle.getGovNumber());
        existingVehicle.setYear(vehicle.getYear());
        existingVehicle.setAutoBrand(vehicle.getAutoBrand());
        vehicleRepository.save(existingVehicle);
    }

    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }
}

