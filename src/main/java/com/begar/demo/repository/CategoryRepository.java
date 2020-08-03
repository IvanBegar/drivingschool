package com.begar.demo.repository;

import com.begar.demo.entity.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from category;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Category category = new Category();
                category.setIdCategory(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setCategoryPayment(resultSet.getDouble(3));
                category.setStudyTime(resultSet.getString(4));
                categories.add(category);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategory(int id) {
        Category category = new Category();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from category where idCategory = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setIdCategory(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setCategoryPayment(resultSet.getDouble(3));
                category.setStudyTime(resultSet.getString(4));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public void addCategory(Category category) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "insert into category (name, categoryPayment, studyTime) values (?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setDouble(2, category.getCategoryPayment());
            preparedStatement.setString(3, category.getStudyTime());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "update category set name = ?, categoryPayment = ?, studyTime = ? where idCategory = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setDouble(2, category.getCategoryPayment());
            preparedStatement.setString(3,category.getStudyTime());
            preparedStatement.setInt(4,category.getIdCategory());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "delete from category where idCategory = ?;";
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
