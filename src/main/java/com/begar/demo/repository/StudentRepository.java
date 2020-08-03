package com.begar.demo.repository;

import com.begar.demo.dto.StudentDocumentsDTO;
import com.begar.demo.dto.StudentPerCategoryDTO;
import com.begar.demo.entity.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from student;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Student s1 = new Student();
                s1.setIdStudent(resultSet.getInt(1));
                s1.setIdGroup(resultSet.getInt(2));
                s1.setFirstName(resultSet.getString(3));
                s1.setMiddleName(resultSet.getString(4));
                s1.setLastName(resultSet.getString(5));
                s1.setDateOfBirth(resultSet.getString(6));
                s1.setAddress(resultSet.getString(7));
                s1.setPhone(resultSet.getString(8));
                students.add(s1);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return students;
        }

    public Student getStudent(int id) {
        Student s1 = new Student();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from mydb.student where idStudent = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                s1.setIdStudent(resultSet.getInt(1));
                s1.setIdGroup(resultSet.getInt(2));
                s1.setFirstName(resultSet.getString(3));
                s1.setMiddleName(resultSet.getString(4));
                s1.setLastName(resultSet.getString(5));
                s1.setDateOfBirth(resultSet.getString(6));
                s1.setAddress(resultSet.getString(7));
                s1.setPhone(resultSet.getString(8));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s1;
    }

    public void addStudent(Student s1) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "insert into student (idGroup, firstName, middleName, lastName, dateOfBirth, adress, phone) values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, s1.getIdGroup());
            preparedStatement.setString(2, s1.getFirstName());
            preparedStatement.setString(3, s1.getMiddleName());
            preparedStatement.setString(4, s1.getLastName());
            preparedStatement.setString(5, s1.getDateOfBirth());
            preparedStatement.setString(6, s1.getAddress());
            preparedStatement.setString(7, s1.getPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student s1) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "update student set idGroup = ?, firstName = ?, middleName = ?, lastName = ?, dateOfBirth = ?, adress = ?, phone = ? where idStudent = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, s1.getIdGroup());
            preparedStatement.setString(2, s1.getFirstName());
            preparedStatement.setString(3, s1.getMiddleName());
            preparedStatement.setString(4, s1.getLastName());
            preparedStatement.setString(5, s1.getDateOfBirth());
            preparedStatement.setString(6, s1.getAddress());
            preparedStatement.setString(7, s1.getPhone());
            preparedStatement.setInt(8, s1.getIdStudent());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "delete from student where idStudent = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StudentPerCategoryDTO getStudentsPerCategory(String cat) {
        StudentPerCategoryDTO sdto1 = new StudentPerCategoryDTO();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select category.name, idStudent, student.idGroup, lastName, firstName, middleName, dateOfBirth, phone, adress from student \n" +
                    "inner join mydb.groups on student.idGroup=mydb.groups.idGroup \n" +
                    "inner join category on mydb.groups.idCategory=category.idCategory\n" +
                    "where name = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, cat);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sdto1.setCategoryName(resultSet.getString(1));
                sdto1.setIdStudent(resultSet.getInt(2));
                sdto1.setIdGroup(resultSet.getInt(3));
                sdto1.setLastName(resultSet.getString(4));
                sdto1.setFirstName(resultSet.getString(5));
                sdto1.setMiddleName(resultSet.getString(6));
                sdto1.setDateOfBirth(resultSet.getString(7));
                sdto1.setPhone(resultSet.getString(8));
                sdto1.setAddress(resultSet.getString(9));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sdto1;
    }

    public StudentDocumentsDTO getStudentDocuments(int id) {
        StudentDocumentsDTO studentDocumentsDTO = new StudentDocumentsDTO();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "SELECT student.firstName,student.lastName, photo, mainDocumentsCopies, medicalСertificate from documents\n" +
                    "inner join student on documents.idStudent=student.idStudent\n" +
                    "where documents.idStudent = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                studentDocumentsDTO.setFirstName(resultSet.getString(1));
                studentDocumentsDTO.setLastName(resultSet.getString(2));
                studentDocumentsDTO.setPhoto(resultSet.getString(3));
                studentDocumentsDTO.setMainDocumentsCopies(resultSet.getString(4));
                studentDocumentsDTO.setMedicalCertificate(resultSet.getString(5));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentDocumentsDTO;
    }

    public List<Student> getStudentsPerGroup(int id) {
        List<Student> students = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "select * from student \n" +
                    "inner join mydb.groups on student.idGroup=mydb.groups.idGroup \n" +
                    "where mydb.groups.idGroup = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student s1 = new Student();
                s1.setIdStudent(resultSet.getInt(1));
                s1.setIdGroup(resultSet.getInt(2));
                s1.setFirstName(resultSet.getString(3));
                s1.setMiddleName(resultSet.getString(4));
                s1.setLastName(resultSet.getString(5));
                s1.setDateOfBirth(resultSet.getString(6));
                s1.setAddress(resultSet.getString(7));
                s1.setPhone(resultSet.getString(8));
                students.add(s1);
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
