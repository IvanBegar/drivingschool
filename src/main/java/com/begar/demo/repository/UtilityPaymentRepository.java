package com.begar.demo.repository;

import com.begar.demo.entity.UtilityPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UtilityPaymentRepository {

    @Autowired
    private DataSource dataSource;

    public List<UtilityPayment> getUtilityPayments() {
        List<UtilityPayment> utilityPayments = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from utilitypayments;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                UtilityPayment utilityPayment = new UtilityPayment();
                utilityPayment.setIdUtilityPayment(resultSet.getInt(1));
                utilityPayment.setSize(resultSet.getDouble(2));
                utilityPayment.setDate(resultSet.getString(3));
                utilityPayments.add(utilityPayment);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilityPayments;
    }

    public UtilityPayment getUtilityPayment(int id) {
        UtilityPayment utilityPayment = new UtilityPayment();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from utilitypayments where idUtilityPayments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                utilityPayment.setIdUtilityPayment(resultSet.getInt(1));
                utilityPayment.setSize(resultSet.getDouble(2));
                utilityPayment.setDate(resultSet.getString(3));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilityPayment;
    }

    public void addUtilityPayment(UtilityPayment utilityPayment) {
        try {
            Connection con = dataSource.getConnection();
            String query = "insert into utilitypayments (size, date) values (?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setDouble(1, utilityPayment.getSize());
            preparedStatement.setString(2, utilityPayment.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUtilityPayment(UtilityPayment utilityPayment) {
        try {
            Connection con = dataSource.getConnection();
            String query = "update utilitypayments set size = ?, date = ? where idUtilityPayments = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setDouble(1, utilityPayment.getSize());
            preparedStatement.setString(2, utilityPayment.getDate());
            preparedStatement.setInt(3, utilityPayment.getIdUtilityPayment());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUtilityPayment(int id) {
        try {
            Connection con = dataSource.getConnection();
            String query = "delete from utilitypayments where idUtilityPayments = ?;";
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
