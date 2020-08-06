package com.begar.demo.repository;

import com.begar.demo.dto.StudentDTO;
import com.begar.demo.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PaymentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Payment> getPayments() {
        String query = "select * from payment;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            Payment payment = new Payment();
            payment.setIdPayment(resultSet.getInt("idPayment"));
            payment.setStudent(getStudentDTO(resultSet.getInt("idStudent")));
            payment.setDateOfPayment(resultSet.getString("dateOfPayment"));
            payment.setPaymentSize(resultSet.getDouble("paymentSize"));
            payment.setPaymentComment(resultSet.getString("paymentComment"));
            return payment;
        });
    }

    public Payment getPayment(int id) {
        String query = "select * from payment where idPayment = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id},(resultSet, i) -> {
                Payment payment = new Payment();
                payment.setIdPayment(resultSet.getInt("idPayment"));
                payment.setStudent(getStudentDTO(resultSet.getInt("idStudent")));
                payment.setDateOfPayment(resultSet.getString("dateOfPayment"));
                payment.setPaymentSize(resultSet.getDouble("paymentSize"));
                payment.setPaymentComment(resultSet.getString("paymentComment"));
                return payment;
        });
    }

    public void addPayment(Payment payment, int id) {
        String query = "insert into payment (idStudent, dateOfPayment, paymentSize, paymentComment) values (?, ?, ?, ?);";
        jdbcTemplate.update(query, id, payment.getDateOfPayment(), payment.getPaymentSize(), payment.getPaymentComment());
    }

    public void updatePayment(Payment payment, int id) {
        String query = "update payment set idStudent = ?, dateOfPayment = ?, paymentSize = ?, paymentComment = ? where idPayment = ?;";
        jdbcTemplate.update(query, id, payment.getDateOfPayment(), payment.getPaymentSize(), payment.getPaymentComment(), payment.getIdPayment());
    }

    public void deletePayment(int id) {
        String query = "delete from payment where idPayment = ?;";
        jdbcTemplate.update(query, id);
    }

    public StudentDTO getStudentDTO(int id) {
        String query = "select lastName, firstName, middleName, phone from student " +
                "inner join documents on student.idStudent=documents.idStudent " +
                "where student.idStudent = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(resultSet.getString("firstName"));
            studentDTO.setMiddleName(resultSet.getString("middleName"));
            studentDTO.setLastName(resultSet.getString("lastName"));
            studentDTO.setPhone(resultSet.getString("phone"));
            return studentDTO;
        });
    }
}