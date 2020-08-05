package com.begar.demo.repository;

import com.begar.demo.dto.IncomeForPeriodDTO;
import com.begar.demo.dto.IncomePerCategoryDTO;
import com.begar.demo.dto.PaymentForPeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class IncomeRepository {

    @Autowired
    private DataSource dataSource;

    public IncomeForPeriodDTO getIncomeForPeriod(String str, String end) {
        IncomeForPeriodDTO incomeForPeriodDTO = new IncomeForPeriodDTO();
        try {
            Connection con = dataSource.getConnection();
            String query = "select sum(paymentSize) from payment where dateOfPayment between ? and ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, str);
            preparedStatement.setString(2, end);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                incomeForPeriodDTO.setIncomeForPeriod(resultSet.getDouble(1));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeForPeriodDTO;
    }

    public IncomePerCategoryDTO getIncomePerCategory(String cat) {
        IncomePerCategoryDTO incomePerCategoryDTO = new IncomePerCategoryDTO();
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT category.name, SUM(paymentSize) FROM payment \n" +
                    "inner join student on payment.idStudent = student.idStudent\n" +
                    "inner join mydb.groups on student.idGroup=groups.idGroup\n" +
                    "inner join category on groups.idCategory=category.idCategory\n" +
                    "where category.name = ? group by mydb.category.name;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, cat);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                incomePerCategoryDTO.setCategoryName(resultSet.getString(1));
                incomePerCategoryDTO.setIncomePerCategory(resultSet.getDouble(2));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomePerCategoryDTO;
    }

    public PaymentForPeriodDTO getPaymentForPeriod(String str, String end) {
        PaymentForPeriodDTO paymentForPeriodDTO = new PaymentForPeriodDTO();
        try {
            Connection con = dataSource.getConnection();
            String query = "select size from utilitypayments where date between ? and ?\n" +
                    "union \n" +
                    "select size from vehiclepayments where date between ? and ?\n" +
                    "union \n" +
                    "select sum(salary) from teacher;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, str);
            preparedStatement.setString(2, end);
            preparedStatement.setString(3, str);
            preparedStatement.setString(4, end);
            ResultSet resultSet = preparedStatement.executeQuery();
            double sum = 0.0;
            while (resultSet.next()) {
                double res = resultSet.getDouble(1);
                sum = sum + res;
            }
            paymentForPeriodDTO.setPaymentForPeriod(sum);
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentForPeriodDTO;
    }
}
