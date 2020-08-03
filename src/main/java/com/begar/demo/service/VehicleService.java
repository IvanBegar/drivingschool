package com.begar.demo.service;

import com.begar.demo.entity.Vehicle;
import com.begar.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getVehicles() {
        return vehicleRepository.getVehicles();
    }

    public Vehicle getVehicle(int id) {
        return vehicleRepository.getVehicle(id);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.addVehicle(vehicle);
    }

    public String updateVehicle(Vehicle vehicle) {
        String result = "";
        if (vehicleRepository.getVehicle(vehicle.getIdVehicle()).getIdVehicle() == 0) {
            result = "Vehicle don`t exist!";
        } else {
            vehicleRepository.updateVehicle(vehicle);
            result = "Vehicle successfully updated";
        }
        return result;
    }

    public void deleteVehicle(int id) {
        vehicleRepository.deleteVehicle(id);
    }
}

