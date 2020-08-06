package com.begar.demo.repository;

import com.begar.demo.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PaymentRepository {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Payment> getPayments() {
        String query = "select * from payment;";
        return jdbcTemplate.query(query, getPaymentRowMapper());
    }

    public Payment getPayment(int id) {
        String query = "select * from payment where payment_id = ?;";
        return jdbcTemplate.queryForObject(query, getPaymentRowMapper(), id);
    }

    public void addPayment(Payment payment, int id) {
        String query = "insert into payment (student_id, date, size, comment) values (?, ?, ?, ?);";
        jdbcTemplate.update(query, id, payment.getDate(), payment.getSize(), payment.getComment());
    }

    public void updatePayment(Payment payment, int id) {
        String query = "update payment set student_id = ?, date = ?, size = ?, comment = ? where payment_id = ?;";
        jdbcTemplate.update(query, id, payment.getDate(), payment.getSize(), payment.getComment(), payment.getPayment_id());
    }

    public void deletePayment(int id) {
        String query = "delete from payment where payment_id = ?;";
        jdbcTemplate.update(query, id);
    }

    private RowMapper<Payment> getPaymentRowMapper() {
        return (resultSet, i) -> {
            Payment payment = new Payment();
            payment.setPayment_id(resultSet.getInt("payment_id"));
            payment.setStudent(studentRepository.getStudent(resultSet.getInt("student_id")));
            payment.setDate(resultSet.getString("date"));
            payment.setSize(resultSet.getDouble("size"));
            payment.setComment(resultSet.getString("comment"));
            return payment;
        };
    }
}