//package com.begar.demo.repository;
//
//import com.begar.demo.entity.TeacherGroup;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Repository;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class TeacherGroupRepository {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String user;
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    public List<TeacherGroup> getTeacherGroups() {
//        List<TeacherGroup> teacherGroups = new ArrayList<>();
//        try {
//            Connection con = DriverManager.getConnection(url, user, password);
//            String query = "select * from teacher_group;";
//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                TeacherGroup teacherGroup = new TeacherGroup();
//                teacherGroup.setIdTeacherGroup(resultSet.getInt(1));
//                teacherGroup.setIdGroup(resultSet.getInt(2));
//                teacherGroup.setIdTeacher(resultSet.getInt(3));
//                teacherGroups.add(teacherGroup);
//            }
//            statement.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return teacherGroups;
//    }
//
//    public TeacherGroup getTeacherGroup(int id) {
//        TeacherGroup teacherGroup = new TeacherGroup();
//        try {
//            Connection con = DriverManager.getConnection(url, user, password);
//            String query = "select * from teacher_group where idTeacherGroup = ?;";
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                teacherGroup.setIdTeacherGroup(resultSet.getInt(1));
//                teacherGroup.setIdTeacher(resultSet.getInt(2));
//                teacherGroup.setIdGroup(resultSet.getInt(3));
//            }
//            preparedStatement.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return teacherGroup;
//    }
//
//    public void addTeacherGroup(TeacherGroup teacherGroup) {
//        try {
//            Connection con = DriverManager.getConnection(url, user, password);
//            String query = "insert into teacher_group (idTeacher, idGroup) values (?, ?);";
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1, teacherGroup.getIdGroup());
//            preparedStatement.setInt(2, teacherGroup.getIdTeacher());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateTeacherGroup(TeacherGroup teacherGroup) {
//        try {
//            Connection con = DriverManager.getConnection(url, user, password);
//            String query = "update teacher_group set idTeacher = ?, idGroup = ? where idTeacherGroup = ?;";
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1, teacherGroup.getIdTeacher());
//            preparedStatement.setInt(2, teacherGroup.getIdGroup());
//            preparedStatement.setInt(3, teacherGroup.getIdTeacherGroup());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteTeacherGroup(int id) {
//        try {
//            Connection con = DriverManager.getConnection(url, user, password);
//            String query = "delete from teacher_group where idTeacherGroup = ?;";
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}