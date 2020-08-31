package com.begar.demo.repository;

import com.begar.demo.dto.IncomeForPeriodDTO;
import com.begar.demo.dto.PaymentForPeriodDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class IncomeRepository {

    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    public IncomeForPeriodDTO getIncomeForPeriod(String str, String end) {
        IncomeForPeriodDTO incomeForPeriodDTO = new IncomeForPeriodDTO();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("select sum(p.size) from Payment as p where p.date between :startDate and :endDate");
            query.setParameter("startDate", str);
            query.setParameter("endDate", end);
            double income = (double) query.getSingleResult();
            incomeForPeriodDTO.setIncomeForPeriod(income);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return incomeForPeriodDTO;
    }

    public PaymentForPeriodDTO getPaymentForPeriod(String str, String end) {
        PaymentForPeriodDTO paymentForPeriodDTO = new PaymentForPeriodDTO();
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("select size from utility_payment where date between ? and ?\n" +
                    "union \n" +
                    "select size from vehicle_payment where date between ? and ?\n" +
                    "union \n" +
                    "select sum(salary) from teacher;");
            query.setParameter(1, str);
            query.setParameter(2, end);
            query.setParameter(3, str);
            query.setParameter(4, end);
            List<BigDecimal> resultList = query.getResultList();
            double payment = 0.0;
            for (BigDecimal d : resultList) {
                double res = d.doubleValue();
                payment = payment + res;
            }
            paymentForPeriodDTO.setPaymentForPeriod(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return paymentForPeriodDTO;
    }
}
