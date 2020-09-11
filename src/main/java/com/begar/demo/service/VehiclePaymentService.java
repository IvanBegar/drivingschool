package com.begar.demo.service;

import com.begar.demo.entity.Vehicle;
import com.begar.demo.entity.VehiclePayment;
import com.begar.demo.exception.NoDataException;
import com.begar.demo.repository.VehiclePaymentRepository;
import com.begar.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclePaymentService {

    @Autowired
    private VehiclePaymentRepository vehiclePaymentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehiclePayment> getVehiclePayments() {
        return vehiclePaymentRepository.findAll();
    }

    public VehiclePayment getVehiclePayment(int id) {
        return vehiclePaymentRepository.findById(id).orElseThrow(() -> new NoDataException("Student with id: " + id + " don't exist!"));
    }

    public void addVehiclePayment(VehiclePayment vehiclePayment, int id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NoDataException("Vehicle with id: " + id + " don't exist!"));
        vehiclePayment.setVehicle(vehicle);
        vehiclePaymentRepository.save(vehiclePayment);
    }

    public void updateVehiclePayment(VehiclePayment vehiclePayment) {
        VehiclePayment existingVehiclePayment = vehiclePaymentRepository.findById(vehiclePayment.getVehicle_payment_id()).orElseThrow(
                () -> new NoDataException("Vehicle Payment with id: " + vehiclePayment.getVehicle_payment_id() + " don't exist!"));
        existingVehiclePayment.setVehicle(vehiclePayment.getVehicle());
        existingVehiclePayment.setDate(vehiclePayment.getDate());
        existingVehiclePayment.setSize(vehiclePayment.getSize());
        existingVehiclePayment.setComment(vehiclePayment.getComment());
        vehiclePaymentRepository.save(existingVehiclePayment);
    }

    public void deleteVehiclePayment(int id) {
        vehiclePaymentRepository.deleteById(id);
    }
}
