package com.begar.demo.repository;

import com.begar.demo.dto.VehicleDTO;
import com.begar.demo.entity.VehiclePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VehiclePaymentRepository {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VehiclePayment> getVehiclePayments() {
        String query = "select * from vehicle_payment;";
        return jdbcTemplate.query(query, getVehiclePaymentRowMapper());
    }

    public VehiclePayment getVehiclePayment(int id) {
        String query = "select * from vehicle_payment where vehicle_payment_id = ?;";
        return jdbcTemplate.queryForObject(query, getVehiclePaymentRowMapper(), id);
    }

    public void addVehiclePayment(VehiclePayment vehiclePayment, int id) {
        String query = "insert into vehicle_payment (vehicle_id, size, date, comment) values (?, ?, ?, ?);";
        jdbcTemplate.update(query, id, vehiclePayment.getSize(), vehiclePayment.getDate(), vehiclePayment.getComment());
    }

    public void updateVehiclePayment(VehiclePayment vehiclePayment, int id) {
        String query = "update vehicle_payment set vehicle_id = ?, size = ?, date = ?, comment = ? where vehicle_payment_id = ?;";
        jdbcTemplate.update(query, id, vehiclePayment.getSize(), vehiclePayment.getDate(), vehiclePayment.getComment(), vehiclePayment.getVehicle_payment_id());
    }

    public void deleteVehiclePayment(int id) {
        String query = "delete from vehicle_payment where vehicle_payment_id = ?;";
        jdbcTemplate.update(query, id);
    }

    public VehicleDTO getVehicleForPaymentsDTO(int id) {
        String query = "select autoBrand, govNumber from vehicle " +
                "where vehicle_id = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            VehicleDTO vehicleForPaymentsDTO = new VehicleDTO();
            vehicleForPaymentsDTO.setAutoBrand(resultSet.getString("autoBrand"));
            vehicleForPaymentsDTO.setGovNumber(resultSet.getString("govNumber"));
            return vehicleForPaymentsDTO;
        });
    }

    private RowMapper<VehiclePayment> getVehiclePaymentRowMapper() {
        return (resultSet, i) -> {
            VehiclePayment vehiclePayment = new VehiclePayment();
            vehiclePayment.setVehicle_payment_id(resultSet.getInt("vehicle_payment_id"));
            vehiclePayment.setVehicle(vehicleRepository.getVehicle(resultSet.getInt("vehicle_id")));
            vehiclePayment.setSize(resultSet.getDouble("size"));
            vehiclePayment.setDate(resultSet.getString("date"));
            vehiclePayment.setComment(resultSet.getString("comment"));
            return vehiclePayment;
        };
    }
}