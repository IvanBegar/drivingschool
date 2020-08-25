package com.begar.demo.service;

import com.begar.demo.entity.VehiclePayment;
import com.begar.demo.exception.DataException;
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

    public void updateVehiclePayment(VehiclePayment vehiclePayment, int id) {
        if (vehiclePaymentRepository.getVehiclePayment(vehiclePayment.getVehicle_payment_id()).getVehicle_payment_id() == 0) {
            throw new DataException("Vehicle payment don`t exist!");
        } else {
            vehiclePaymentRepository.updateVehiclePayment(vehiclePayment, id);
        }
    }

    public void deleteVehiclePayment(int id) {
        vehiclePaymentRepository.deleteVehiclePayment(id);
    }
}
