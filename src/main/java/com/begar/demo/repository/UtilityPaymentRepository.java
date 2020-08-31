package com.begar.demo.repository;

import com.begar.demo.entity.UtilityPayment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UtilityPaymentRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    public List<UtilityPayment> getUtilityPayments() {
        List utilityPayments = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            utilityPayments = session.createQuery("from UtilityPayment ").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return utilityPayments;
    }

    public UtilityPayment getUtilityPayment(int id) {
        UtilityPayment utilityPayment = new UtilityPayment();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            utilityPayment = session.get(UtilityPayment.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return utilityPayment;
    }

    public void addUtilityPayment(UtilityPayment utilityPayment) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(utilityPayment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateUtilityPayment(UtilityPayment utilityPayment) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(utilityPayment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUtilityPayment(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            UtilityPayment utilityPayment = session.get(UtilityPayment.class, id);
            if (utilityPayment != null) {
                session.delete(utilityPayment);
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
