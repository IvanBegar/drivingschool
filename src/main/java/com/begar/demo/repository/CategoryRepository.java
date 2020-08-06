package com.begar.demo.repository;

import com.begar.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoryRepository {

    public static final RowMapper<Category> CATEGORY_ROW_MAPPER = (resultSet, i) -> {
        Category category = new Category();
        category.setCategory_id(resultSet.getInt("category_id"));
        category.setName(resultSet.getString("name"));
        category.setPayment(resultSet.getDouble("payment"));
        category.setStudyTime(resultSet.getString("studyTime"));
        return category;
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories() {
        String query = "select * from category;";
        return jdbcTemplate.query(query, CATEGORY_ROW_MAPPER);
    }

    public Category getCategory(int id) {
        String query = "select * from category where category_id = ?;";
        return jdbcTemplate.queryForObject(query, CATEGORY_ROW_MAPPER, id);
    }

    public void addCategory(Category category) {
        String query = "insert into category (name, payment, studyTime) values (?, ?, ?);";
        jdbcTemplate.update(query, category.getName(), category.getPayment(), category.getStudyTime());
    }

    public void updateCategory(Category category) {
        String query = "update category set name = ?, payment = ?, studyTime = ? where category_id = ?;";
        jdbcTemplate.update(query, category.getName(), category.getPayment(), category.getStudyTime(), category.getCategory_id());
    }

    public void deleteCategory(int id) {
        String query = "delete from category where category_id = ?;";
        jdbcTemplate.update(query, id);
    }
}