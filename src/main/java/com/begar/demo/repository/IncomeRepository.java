package com.begar.demo.repository;

import com.begar.demo.dto.IncomeForPeriodDTO;
import com.begar.demo.dto.IncomePerCategoryDTO;
import com.begar.demo.dto.PaymentForPeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public IncomeForPeriodDTO getIncomeForPeriod(String str, String end) {
        String query = "select sum(paymentSize) from payment where dateOfPayment between ? and ?;";
        return jdbcTemplate.queryForObject(query, new Object[]{str, end}, (resultSet, i) -> {
            IncomeForPeriodDTO incomeForPeriodDTO = new IncomeForPeriodDTO();
            incomeForPeriodDTO.setIncomeForPeriod(resultSet.getDouble("sum(paymentSize)"));
            return incomeForPeriodDTO;
        });
    }

    public IncomePerCategoryDTO getIncomePerCategory(String cat) {
        String query = "select category.name, sum(paymentSize) from payment \n" +
                "inner join student on payment.idStudent = student.idStudent\n" +
                "inner join mydb.groups on student.idGroup=groups.idGroup\n" +
                "inner join category on groups.idCategory=category.idCategory\n" +
                "where category.name = ? group by mydb.category.name;";
        return jdbcTemplate.queryForObject(query, new Object[]{cat}, (resultSet, i) -> {
            IncomePerCategoryDTO incomePerCategoryDTO = new IncomePerCategoryDTO();
            incomePerCategoryDTO.setCategoryName(resultSet.getString("name"));
            incomePerCategoryDTO.setCategoryName(resultSet.getString("sum(paymentSize)"));
            return incomePerCategoryDTO;
        });
    }

    public PaymentForPeriodDTO getPaymentForPeriod(String str, String end) {
        String query = "select size from utilitypayments where date between ? and ?\n" +
                "union \n" +
                "select size from vehiclepayments where date between ? and ?\n" +
                "union \n" +
                "select sum(salary) from teacher;";
        return jdbcTemplate.queryForObject(query, new Object[]{str, end, str, end}, (resultSet, i) -> {
            PaymentForPeriodDTO paymentForPeriodDTO = new PaymentForPeriodDTO();
            double sum = 0.0;
            while (resultSet.next()) {
                double res = resultSet.getDouble(1);
                sum = sum + res;
            }
            paymentForPeriodDTO.setPaymentForPeriod(sum);
            return paymentForPeriodDTO;
        });
    }
}