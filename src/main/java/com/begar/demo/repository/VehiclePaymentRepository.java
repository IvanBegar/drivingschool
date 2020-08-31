package com.begar.demo.repository;

import com.begar.demo.entity.Vehicle;
import com.begar.demo.entity.VehiclePayment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehiclePaymentRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehiclePayment> getVehiclePayments() {
        List vehiclePayments = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            vehiclePayments = session.createQuery("from VehiclePayment ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehiclePayments;
    }

    public VehiclePayment getVehiclePayment(int id) {
        VehiclePayment vehiclePayment = new VehiclePayment();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            vehiclePayment = session.get(VehiclePayment.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehiclePayment;
    }

    public void addVehiclePayment(VehiclePayment vehiclePayment, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Vehicle vehicle = vehicleRepository.getVehicle(id);
            vehiclePayment.setVehicle(vehicle);
            session.save(vehiclePayment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateVehiclePayment(VehiclePayment vehiclePayment, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Vehicle vehicle = vehicleRepository.getVehicle(id);
            vehiclePayment.setVehicle(vehicle);
            session.update(vehiclePayment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteVehiclePayment(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            VehiclePayment vehiclePayment = session.get(VehiclePayment.class, id);
            if (vehiclePayment != null) {
                    session.delete(vehiclePayment);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
