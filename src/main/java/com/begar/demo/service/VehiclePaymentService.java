package com.begar.demo.service;

import com.begar.demo.entity.VehiclePayment;
import com.begar.demo.repository.VehiclePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiclePaymentService {

    @Autowired
    private VehiclePaymentRepository vehiclePaymentRepository;

    public List<VehiclePayment> getVehiclePayments() {
        return vehiclePaymentRepository.getVehiclePayments();
    }

    public VehiclePayment getVehiclePayment(int id) {
        return vehiclePaymentRepository.getVehiclePayment(id);
    }

    public void addVehiclePayment(VehiclePayment vehiclePayment) {
        vehiclePaymentRepository.addVehiclePayment(vehiclePayment);
    }

    public String updateVehiclePayment(VehiclePayment vehiclePayment) {
        String result = "";
        if (vehiclePaymentRepository.getVehiclePayment(vehiclePayment.getIdVehiclePayment()).getIdVehiclePayment() == 0) {
            result = "VehiclePayment don`t exist!";
        } else {
            vehiclePaymentRepository.updateVehiclePayment(vehiclePayment);
            result = "VehiclePayment successfully updated";
        }
        return result;
    }

    public void deleteVehiclePayment(int id) {
        vehiclePaymentRepository.deleteVehiclePayment(id);
    }
}