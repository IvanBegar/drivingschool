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
        return vehicleRepository.getVehicles();
    }

    public Vehicle getVehicle(int id) {
        return vehicleRepository.getVehicle(id);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.addVehicle(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        if (vehicleRepository.getVehicle(vehicle.getVehicle_id()).getVehicle_id() == 0) {
            throw new DataException("Vehicle don`t exist!");
        } else {
            vehicleRepository.updateVehicle(vehicle);
        }
    }

    public void deleteVehicle(int id) {
        vehicleRepository.deleteVehicle(id);
    }

    public void addGroupToVehicle(int id1, int id2) {
        vehicleRepository.addGroupToVehicle(id1, id2);
    }
}

