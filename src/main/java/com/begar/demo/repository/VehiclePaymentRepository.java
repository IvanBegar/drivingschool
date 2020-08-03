package com.begar.demo.repository;

import com.begar.demo.entity.VehiclePayment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehiclePaymentRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public List<VehiclePayment> getVehiclePayments() {
        List<VehiclePayment> vehiclePayments = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from vehiclepayments;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                VehiclePayment vehiclePayment = new VehiclePayment();
                vehiclePayment.setIdVehiclePayment(resultSet.getInt(1));
                vehiclePayment.setIdVehicle(resultSet.getInt(2));
                vehiclePayment.setSize(resultSet.getDouble(3));
                vehiclePayment.setDate(resultSet.getString(4));
                vehiclePayment.setPaymentComment(resultSet.getString(5));
                vehiclePayments.add(vehiclePayment);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiclePayments;
    }

    public VehiclePayment getVehiclePayment(int id) {
        VehiclePayment vehiclePayment = new VehiclePayment();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from vehiclepayments where idVehiclePayments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                vehiclePayment.setIdVehiclePayment(resultSet.getInt(1));
                vehiclePayment.setIdVehicle(resultSet.getInt(2));
                vehiclePayment.setSize(resultSet.getDouble(3));
                vehiclePayment.setDate(resultSet.getString(4));
                vehiclePayment.setPaymentComment((resultSet.getString(5)));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiclePayment;
    }

    public void addVehiclePayment(VehiclePayment vehiclePayment) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "insert into vehiclepayments (idVehicle, size, date, paymentComment) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, vehiclePayment.getIdVehicle());
            preparedStatement.setDouble(2, vehiclePayment.getSize());
            preparedStatement.setString(3, vehiclePayment.getDate());
            preparedStatement.setString(4, vehiclePayment.getPaymentComment());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVehiclePayment(VehiclePayment vehiclePayment) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "update vehiclepayments set idVehicle = ?, size = ?, date = ?, paymentComment = ? where idVehiclePayments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, vehiclePayment.getIdVehicle());
            preparedStatement.setDouble(2, vehiclePayment.getSize());
            preparedStatement.setString(3, vehiclePayment.getDate());
            preparedStatement.setString(4, vehiclePayment.getPaymentComment());
            preparedStatement.setInt(5, vehiclePayment.getIdVehiclePayment());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehiclePayment(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "delete from vehiclepayments where idVehiclePayments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}