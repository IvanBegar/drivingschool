package com.begar.demo.repository;

import com.begar.demo.entity.UtilityPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UtilityPaymentRepository {

    public static final RowMapper<UtilityPayment> UTILITY_PAYMENT_ROW_MAPPER = (resultSet, i) -> {
        UtilityPayment utilityPayment = new UtilityPayment();
        utilityPayment.setUtility_payment_id(resultSet.getInt("utility_payment_id"));
        utilityPayment.setSize(resultSet.getDouble("size"));
        utilityPayment.setDate(resultSet.getString("date"));
        return utilityPayment;
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UtilityPayment> getUtilityPayments() {
        String query = "select * from utility_payment;";
        return jdbcTemplate.query(query, UTILITY_PAYMENT_ROW_MAPPER);
    }

    public UtilityPayment getUtilityPayment(int id) {
        String query = "select * from utility_payment where utility_payment_id = ?;";
        return jdbcTemplate.queryForObject(query, UTILITY_PAYMENT_ROW_MAPPER, id);
    }

    public void addUtilityPayment(UtilityPayment utilityPayment) {
        String query = "insert into utility_payment (size, date) values (?, ?);";
        jdbcTemplate.update(query, utilityPayment.getSize(), utilityPayment.getDate());
    }

    public void updateUtilityPayment(UtilityPayment utilityPayment) {
        String query = "update utility_payment set size = ?, date = ? where utility_payment_id = ?;";
        jdbcTemplate.update(query, utilityPayment.getSize(), utilityPayment.getDate(), utilityPayment.getUtility_payment_id());
    }

    public void deleteUtilityPayment(int id) {
        String query = "delete from utility_payment where utility_payment_id = ?;";
        jdbcTemplate.update(query, id);
    }
}