package com.begar.demo.repository;

import com.begar.demo.entity.GroupVehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupVehicleRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public List<GroupVehicle> getGroupVehicles() {
        List<GroupVehicle> groupVehicles = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from group_vehicle;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                GroupVehicle groupVehicle = new GroupVehicle();
                groupVehicle.setIdGroupVehicle(resultSet.getInt(1));
                groupVehicle.setIdGroup(resultSet.getInt(2));
                groupVehicle.setIdVehicle(resultSet.getInt(3));
                groupVehicles.add(groupVehicle);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupVehicles;
    }

    public GroupVehicle getGroupVehicle(int id) {
        GroupVehicle groupVehicle = new GroupVehicle();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from group_vehicle where idGroupVehicle = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                groupVehicle.setIdGroupVehicle(resultSet.getInt(1));
                groupVehicle.setIdGroup(resultSet.getInt(2));
                groupVehicle.setIdVehicle(resultSet.getInt(3));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupVehicle;
    }

    public void addGroupVehicle(GroupVehicle groupVehicle) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "insert into group_vehicle (idGroup, idVehicle) values (?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, groupVehicle.getIdGroup());
            preparedStatement.setInt(2, groupVehicle.getIdVehicle());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroupVehicle(GroupVehicle groupVehicle) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "update group_vehicle set idGroup = ?, idVehicle = ? where idGroupVehicle = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, groupVehicle.getIdGroup());
            preparedStatement.setInt(2, groupVehicle.getIdVehicle());
            preparedStatement.setInt(3,groupVehicle.getIdGroupVehicle());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroupVehicle(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "delete from group_vehicle where idGroupVehicle = ?;";
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