package com.begar.demo.repository;

import com.begar.demo.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Payment> getPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from payment;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setIdPayment(resultSet.getInt(1));
                payment.setIdStudent(resultSet.getInt(2));
                payment.setDateOfPayment(resultSet.getString(3));
                payment.setPaymentSize(resultSet.getDouble(4));
                payment.setPaymentComment(resultSet.getString(5));
                payments.add(payment);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public Payment getPayment(int id) {
        Payment payment = new Payment();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from payment where idPayment = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                payment.setIdPayment(resultSet.getInt(1));
                payment.setIdStudent(resultSet.getInt(2));
                payment.setDateOfPayment(resultSet.getString(3));
                payment.setPaymentSize(resultSet.getDouble(4));
                payment.setPaymentComment(resultSet.getString(5));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public void addPayment(Payment payment) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "insert into payment (idStudent, dateOfPayment, paymentSize, paymentComment) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, payment.getIdStudent());
            preparedStatement.setString(2, payment.getDateOfPayment());
            preparedStatement.setDouble(3, payment.getPaymentSize());
            preparedStatement.setString(4, payment.getPaymentComment());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePayment(Payment payment) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "update payment set idStudent = ?, dateOfPayment = ?, paymentSize = ?, paymentComment = ? where idPayment = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, payment.getIdStudent());
            preparedStatement.setString(2, payment.getDateOfPayment());
            preparedStatement.setDouble(3,payment.getPaymentSize());
            preparedStatement.setString(4,payment.getPaymentComment());
            preparedStatement.setInt(5,payment.getIdPayment());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "delete from payment where idPayment = ?;";
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