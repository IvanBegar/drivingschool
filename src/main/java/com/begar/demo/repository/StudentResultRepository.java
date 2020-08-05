package com.begar.demo.repository;

import com.begar.demo.entity.StudentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentResultRepository {

    @Autowired
    private DataSource dataSource;

    public List<StudentResult> getStudentResults() {
        List<StudentResult> studentResults = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from studentresults;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                StudentResult studentResult = new StudentResult();
                studentResult.setIdResult(resultSet.getInt(1));
                studentResult.setIdStudent(resultSet.getInt(2));
                studentResult.setDateOfExam(resultSet.getString(3));
                studentResult.setResultInCenter(resultSet.getString(4));
                studentResult.setResultInSchool(resultSet.getString(5));
                studentResults.add(studentResult);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentResults;
    }

    public StudentResult getStudentResult(int id) {
        StudentResult studentResult = new StudentResult();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from studentresults where idResult = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                studentResult.setIdResult(resultSet.getInt(1));
                studentResult.setIdStudent(resultSet.getInt(2));
                studentResult.setDateOfExam(resultSet.getString(3));
                studentResult.setResultInCenter(resultSet.getString(4));
                studentResult.setResultInSchool(resultSet.getString(5));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentResult;
    }

    public void addStudentResult(StudentResult studentResult) {
        try {
            Connection con = dataSource.getConnection();
            String query = "insert into studentresults (idStudent, dateOfExam, resultInCenter, resultInSchool) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentResult.getIdStudent());
            preparedStatement.setString(2, studentResult.getDateOfExam());
            preparedStatement.setString(3, studentResult.getResultInCenter());
            preparedStatement.setString(4, studentResult.getResultInSchool());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudentResult(StudentResult studentResult) {
        try {
            Connection con = dataSource.getConnection();
            String query = "update studentresults set idStudent = ?, dateOfExam = ?, resultInCenter = ?, resultInSchool = ? where idResult = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentResult.getIdResult());
            preparedStatement.setString(2, studentResult.getDateOfExam());
            preparedStatement.setString(3, studentResult.getResultInCenter());
            preparedStatement.setString(4, studentResult.getResultInSchool());
            preparedStatement.setInt(5, studentResult.getIdStudent());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentResult(int id) {
        try {
            Connection con = dataSource.getConnection();
            String query = "delete from studentresults where idResult = ?;";
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
