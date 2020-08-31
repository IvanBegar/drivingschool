package com.begar.demo.repository;

import com.begar.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private StudentRepository studentRepository;

    public List<Payment> getPayments() {
        List payments = new ArrayList();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            payments = session.createQuery("from Payment").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return payments;
    }

    public Payment getPayment(int id) {
        Payment payment = new Payment();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            payment = session.get(Payment.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return payment;
    }

    public void addPayment(Payment payment, int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student = studentRepository.getStudent(id);
            payment.setStudent(student);
            session.save(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updatePayment(Payment payment) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePayment(int id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Payment payment = session.get(Payment.class, id);
            if (payment != null) {
                session.delete(payment);
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
