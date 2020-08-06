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

    public void addVehiclePayment(VehiclePayment vehiclePayment, int id) {
        vehiclePaymentRepository.addVehiclePayment(vehiclePayment, id);
    }

    public String updateVehiclePayment(VehiclePayment vehiclePayment, int id) {
        String result = "";
        if (vehiclePaymentRepository.getVehiclePayment(vehiclePayment.getVehicle_payment_id()).getVehicle_payment_id() == 0) {
            result = "VehiclePayment don`t exist!";
        } else {
            vehiclePaymentRepository.updateVehiclePayment(vehiclePayment, id);
            result = "VehiclePayment successfully updated";
        }
        return result;
    }

    public void deleteVehiclePayment(int id) {
        vehiclePaymentRepository.deleteVehiclePayment(id);
    }
}