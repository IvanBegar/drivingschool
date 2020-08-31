package com.begar.demo.repository;

import com.begar.demo.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    public List<Vehicle> getVehicles() {
        List vehicles = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            vehicles = session.createQuery("from Vehicle ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicles;
    }

    public Vehicle getVehicle(int id) {
        Vehicle vehicle = new Vehicle();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicle;
    }

    public void addVehicle(Vehicle vehicle) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteVehicle(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Vehicle vehicle = session.get(Vehicle.class, id);
            if (vehicle != null) {
                session.delete(vehicle);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void addGroupToVehicle(int id1, int id2) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "insert into group_vehicle (group_id, vehicle_id) values (?, ?);";
            Query query = session.createSQLQuery(sql).setParameter(1, id1).setParameter(2, id2);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

