package com.begar.demo.repository;

import com.begar.demo.entity.UtilityPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UtilityPaymentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UtilityPayment> getUtilityPayments() {
        String query = "select * from utilitypayments;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            UtilityPayment utilityPayment = new UtilityPayment();
            utilityPayment.setIdUtilityPayment(resultSet.getInt("idUtilityPayments"));
            utilityPayment.setSize(resultSet.getDouble("size"));
            utilityPayment.setDate(resultSet.getString("date"));
            return utilityPayment;
        });
    }

    public UtilityPayment getUtilityPayment(int id) {
        String query = "select * from utilitypayments where idUtilityPayments = ?;";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, i) -> {
            UtilityPayment utilityPayment = new UtilityPayment();
            utilityPayment.setIdUtilityPayment(resultSet.getInt("idUtilityPayment"));
            utilityPayment.setSize(resultSet.getDouble("size"));
            utilityPayment.setDate(resultSet.getString("date"));
            return utilityPayment;
        });
    }

    public void addUtilityPayment(UtilityPayment utilityPayment) {
        String query = "insert into utilitypayments (size, date) values (?, ?);";
        jdbcTemplate.update(query, utilityPayment.getSize(), utilityPayment.getDate());
    }

    public void updateUtilityPayment(UtilityPayment utilityPayment) {
        String query = "update utilitypayments set size = ?, date = ? where idUtilityPayments = ?;";
        jdbcTemplate.update(query, utilityPayment.getSize(), utilityPayment.getDate(), utilityPayment.getIdUtilityPayment());
    }

    public void deleteUtilityPayment(int id) {
        String query = "delete from utilitypayments where idUtilityPayments = ?;";
        jdbcTemplate.update(query, id);
    }
}