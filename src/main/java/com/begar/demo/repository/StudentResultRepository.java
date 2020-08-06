package com.begar.demo.repository;

import com.begar.demo.entity.StudentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentResultRepository {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StudentResult> getStudentResults() {
        String query = "select * from student_result;";
        return jdbcTemplate.query(query, getStudentResultRowMapper());
    }

    public StudentResult getStudentResult(int id) {
        String query = "select * from student_result where result_id = ?;";
        return jdbcTemplate.queryForObject(query, getStudentResultRowMapper(), id);
    }

    public void addStudentResult(StudentResult studentResult, int id) {
        String query = "insert into student_result (student_id, dateOfExam, resultInCenter, resultInSchool) values (?, ?, ?, ?);";
        jdbcTemplate.update(query, id, studentResult.getDateOfExam(), studentResult.getResultInCenter(), studentResult.getResultInSchool());
    }

    public void updateStudentResult(StudentResult studentResult, int id) {
        String query = "update student_result set student_id = ?, dateOfExam = ?, resultInCenter = ?, resultInSchool = ? where result_id = ?;";
        jdbcTemplate.update(query, id, studentResult.getDateOfExam(), studentResult.getResultInCenter(), studentResult.getResultInSchool(), studentResult.getResult_id());
    }

    public void deleteStudentResult(int id) {
        String query = "delete from student_result where result_id = ?;";
        jdbcTemplate.update(query, id);
    }

    private RowMapper<StudentResult> getStudentResultRowMapper() {
        return (resultSet, i) -> {
            StudentResult studentResult = new StudentResult();
            studentResult.setResult_id(resultSet.getInt("result_id"));
            studentResult.setStudent(studentRepository.getStudent(resultSet.getInt("student_id")));
            studentResult.setDateOfExam(resultSet.getString("dateOfExam"));
            studentResult.setResultInCenter(resultSet.getString("resultInCenter"));
            studentResult.setResultInSchool(resultSet.getString("resultInSchool"));
            return studentResult;
        };
    }
}
