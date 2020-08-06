package com.begar.demo.repository;

import com.begar.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories() {
        String query = "select * from category;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            Category category = new Category();
            category.setIdCategory(resultSet.getInt("idCategory"));
            category.setName(resultSet.getString("name"));
            category.setCategoryPayment(resultSet.getDouble("categoryPayment"));
            category.setStudyTime(resultSet.getString("studyTime"));
            return category;
        });
    }

    public Category getCategory(int id) {
        String query = "select * from category where idCategory = ?;";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, i) -> {
            Category category = new Category();
            category.setIdCategory(resultSet.getInt("idCategory"));
            category.setName(resultSet.getString("name"));
            category.setCategoryPayment(resultSet.getDouble("categoryPayment"));
            category.setStudyTime(resultSet.getString("studyTime"));
            return category;
        });
    }

    public void addCategory(Category category) {
        String query = "insert into category (name, categoryPayment, studyTime) values (?, ?, ?);";
        jdbcTemplate.update(query, category.getName(), category.getCategoryPayment(), category.getStudyTime());
    }

    public void updateCategory(Category category) {
        String query = "update category set name = ?, categoryPayment = ?, studyTime = ? where idCategory = ?;";
        jdbcTemplate.update(query, category.getName(), category.getCategoryPayment(), category.getStudyTime(), category.getIdCategory());
    }

    public void deleteCategory(int id) {
        String query = "delete from category where idCategory = ?;";
        jdbcTemplate.update(query, id);
    }
}