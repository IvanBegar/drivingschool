package com.begar.demo.repository;

import com.begar.demo.entity.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;


    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from vehicle;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setIdVehicle(resultSet.getInt(1));
                vehicle.setAutoBrand(resultSet.getString(2));
                vehicle.setYear(resultSet.getString(3));
                vehicles.add(vehicle);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public Vehicle getVehicle(int id) {
        Vehicle vehicle = new Vehicle();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from vehicle where idVehicle = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                vehicle.setIdVehicle(resultSet.getInt(1));
                vehicle.setAutoBrand(resultSet.getString(2));
                vehicle.setYear(resultSet.getString(3));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public void addVehicle(Vehicle vehicle) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "insert into vehicle (autoBrand, year) values (?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, vehicle.getAutoBrand());
            preparedStatement.setString(2, vehicle.getYear());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "update vehicle set autoBrand = ?, year = ? where idVehicle = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, vehicle.getAutoBrand());
            preparedStatement.setString(2, vehicle.getYear());
            preparedStatement.setInt(3,vehicle.getIdVehicle());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "delete from vehicle where idVehicle = ?;";
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

