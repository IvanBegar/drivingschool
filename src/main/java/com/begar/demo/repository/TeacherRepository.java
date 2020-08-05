package com.begar.demo.repository;

import com.begar.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {

    @Autowired
    private DataSource dataSource;

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from teacher;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Teacher t1 = new Teacher();
                t1.setIdTeacher(resultSet.getInt(1));
                t1.setFirstName(resultSet.getString(2));
                t1.setMiddleName(resultSet.getString(3));
                t1.setLastName(resultSet.getString(4));
                t1.setDateOfBirth(resultSet.getString(5));
                t1.setAddress(resultSet.getString(6));
                t1.setPhone(resultSet.getString(7));
                t1.setSalary(resultSet.getDouble(8));
                teachers.add(t1);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public Teacher getTeacher(int id) {
        Teacher t1 = new Teacher();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from teacher where idTeacher = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                t1.setIdTeacher(resultSet.getInt(1));
                t1.setFirstName(resultSet.getString(2));
                t1.setMiddleName(resultSet.getString(3));
                t1.setLastName(resultSet.getString(4));
                t1.setDateOfBirth(resultSet.getString(5));
                t1.setAddress(resultSet.getString(6));
                t1.setPhone(resultSet.getString(7));
                t1.setSalary(resultSet.getDouble(8));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t1;
    }

    public void addTeacher(Teacher t1) {
        try {
            Connection con = dataSource.getConnection();
            String query = "insert into teacher (firstName, middleName, lastName, dateOfBirth, adress, phone, salary) values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, t1.getFirstName());
            preparedStatement.setString(2, t1.getMiddleName());
            preparedStatement.setString(3, t1.getLastName());
            preparedStatement.setString(4, t1.getDateOfBirth());
            preparedStatement.setString(5, t1.getAddress());
            preparedStatement.setString(6, t1.getPhone());
            preparedStatement.setDouble(7, t1.getSalary());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeacher(Teacher t1) {
        try {
            Connection con = dataSource.getConnection();
            String query = "update teacher set firstName = ?, middleName = ?, lastName = ?, dateOfBirth = ?, adress = ?, phone = ?, salary = ? where idTeacher = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, t1.getFirstName());
            preparedStatement.setString(2, t1.getMiddleName());
            preparedStatement.setString(3, t1.getLastName());
            preparedStatement.setString(4, t1.getDateOfBirth());
            preparedStatement.setString(5, t1.getAddress());
            preparedStatement.setString(6, t1.getPhone());
            preparedStatement.setDouble(7, t1.getSalary());
            preparedStatement.setInt(8, t1.getIdTeacher());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(int id) {
        try {
            Connection con = dataSource.getConnection();
            String query = "delete from teacher where idTeacher = ?;";
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

