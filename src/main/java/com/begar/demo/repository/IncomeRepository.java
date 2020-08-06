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
        String query = "select sum(size) from payment where date between ? and ?;";
        return jdbcTemplate.queryForObject(query, new Object[]{str, end}, (resultSet, i) -> {
            IncomeForPeriodDTO incomeForPeriodDTO = new IncomeForPeriodDTO();
            incomeForPeriodDTO.setIncomeForPeriod(resultSet.getDouble("sum(size)"));
            return incomeForPeriodDTO;
        });
    }

    public IncomePerCategoryDTO getIncomePerCategory(String cat) {
        String query = "select category.name, sum(size) from payment \n" +
                "inner join student on payment.student_id = student.student_id\n" +
                "inner join mydb.group on student.group_id=groups.group_id\n" +
                "inner join category on group.category_id=category.category_id\n" +
                "where category.name = ? group by mydb.category.name;";
        return jdbcTemplate.queryForObject(query, new Object[]{cat}, (resultSet, i) -> {
            IncomePerCategoryDTO incomePerCategoryDTO = new IncomePerCategoryDTO();
            incomePerCategoryDTO.setCategoryName(resultSet.getString("name"));
            incomePerCategoryDTO.setCategoryName(resultSet.getString("sum(size)"));
            return incomePerCategoryDTO;
        });
    }

    public PaymentForPeriodDTO getPaymentForPeriod(String str, String end) {
        String query = "select size from utility_payment where date between ? and ?\n" +
                "union \n" +
                "select size from vehicle_payment where date between ? and ?\n" +
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