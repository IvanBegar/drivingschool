package com.begar.demo.repository;

import com.begar.demo.dto.StudentDocumentsDTO;
import com.begar.demo.dto.StudentPerCategoryDTO;
import com.begar.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getStudents() {
        String query = "select * from student;";
        return jdbcTemplate.query(query, getStudentRowMapper("student_id", "group_id"));
    }

    public Student getStudent(int id) {
        String query = "select * from mydb.student where student_id = ?;";
        return jdbcTemplate.queryForObject(query, getStudentRowMapper("student_id", "group_id"), id);
    }

    public void addStudent(Student student, int id) {
        String query = "insert into student (group_id, firstName, middleName, lastName, dateOfBirth, address, phone) values (?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, id, student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getDateOfBirth(), student.getAddress(), student.getPhone());
    }

    public void updateStudent(Student student, int id) {
        String query = "update student set group_id = ?, firstName = ?, middleName = ?, lastName = ?, dateOfBirth = ?, address = ?, phone = ? where student_id = ?;";
        jdbcTemplate.update(query, id, student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getDateOfBirth(), student.getAddress(), student.getPhone(), student.getStudent_id());
    }

    public void deleteStudent(int id) {
        String query = "delete from student where student_id = ?;";
        jdbcTemplate.update(query, id);
    }

    public StudentPerCategoryDTO getStudentsPerCategory(String cat) {
        String query = "select category.name, student_id, student.group_id, lastName, firstName, middleName, dateOfBirth, phone, address from student \n" +
                    "inner join mydb.group on student.group_id=mydb.group.group_id \n" +
                    "inner join category on mydb.group.category_id=category.category_id\n" +
                    "where name = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {cat},(resultSet, i) -> {
                StudentPerCategoryDTO studentPerCategoryDTO = new StudentPerCategoryDTO();
                studentPerCategoryDTO.setCategoryName(resultSet.getString("name"));
                studentPerCategoryDTO.setIdStudent(resultSet.getInt("student_id"));
                studentPerCategoryDTO.setIdGroup(resultSet.getInt("group_id"));
                studentPerCategoryDTO.setLastName(resultSet.getString("lastName"));
                studentPerCategoryDTO.setFirstName(resultSet.getString("firstName"));
                studentPerCategoryDTO.setMiddleName(resultSet.getString("middleName"));
                studentPerCategoryDTO.setDateOfBirth(resultSet.getString("dateOfBirth"));
                studentPerCategoryDTO.setPhone(resultSet.getString("phone"));
                studentPerCategoryDTO.setAddress(resultSet.getString("address"));
        return studentPerCategoryDTO;
        });
    }

    public StudentDocumentsDTO getStudentDocuments(int id) {
        String query = "SELECT student.firstName,student.lastName, photo, mainDocumentsCopies, medicalСertificate from document\n" +
                    "inner join student on document.student_id=student.student_id\n" +
                    "where documents.student_id = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id},(resultSet, i) -> {
            StudentDocumentsDTO studentDocumentsDTO = new StudentDocumentsDTO();
                studentDocumentsDTO.setFirstName(resultSet.getString("firstName"));
                studentDocumentsDTO.setLastName(resultSet.getString("lastName"));
                studentDocumentsDTO.setPhoto(resultSet.getString("photo"));
                studentDocumentsDTO.setMainDocumentsCopies(resultSet.getString("mainDocumentsCopies"));
                studentDocumentsDTO.setMedicalCertificate(resultSet.getString("medicalCertificate"));
        return studentDocumentsDTO;
        });
    }

    public List<Student> getStudentsPerGroup(int id) {
        String query = "select * from student \n" +
                    "inner join mydb.group on student.group_id=mydb.group.group_id \n" +
                    "where mydb.group.group_id = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, getStudentRowMapper("idStudent", "idGroup"));
    }

    private RowMapper<Student> getStudentRowMapper(String student_id, String group_id) {
        return (resultSet, i) -> {
            Student student = new Student();
            student.setStudent_id(resultSet.getInt(student_id));
            student.setGroup(groupRepository.getGroup(resultSet.getInt(group_id)));
            student.setFirstName(resultSet.getString("firstName"));
            student.setMiddleName(resultSet.getString("middleName"));
            student.setLastName(resultSet.getString("lastName"));
            student.setDateOfBirth(resultSet.getString("dateOfBirth"));
            student.setPhone(resultSet.getString("phone"));
            student.setAddress(resultSet.getString("address"));
            return student;
        };
    }
}
