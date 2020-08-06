package com.begar.demo.repository;

import com.begar.demo.dto.GroupForStudentDTO;
import com.begar.demo.dto.StudentDocumentsDTO;
import com.begar.demo.dto.StudentPerCategoryDTO;
import com.begar.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getStudents() {
        String query = "select * from student;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            Student student = new Student();
            student.setIdStudent(resultSet.getInt("idStudent"));
            student.setGroup(getGroupForStudentDTO(resultSet.getInt("idGroup")));
            student.setFirstName(resultSet.getString("firstName"));
            student.setMiddleName(resultSet.getString("middleName"));
            student.setLastName(resultSet.getString("lastName"));
            student.setDateOfBirth(resultSet.getString("dateOfBirth"));
            student.setPhone(resultSet.getString("phone"));
            student.setAddress(resultSet.getString("address"));
            return student;
        });
    }

    public Student getStudent(int id) {
        String query = "select * from mydb.student where idStudent = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id},(resultSet, i) -> {
            Student student = new Student();
            student.setIdStudent(resultSet.getInt("idStudent"));
            student.setGroup(getGroupForStudentDTO(resultSet.getInt("idGroup")));
            student.setFirstName(resultSet.getString("firstName"));
            student.setMiddleName(resultSet.getString("middleName"));
            student.setLastName(resultSet.getString("lastName"));
            student.setDateOfBirth(resultSet.getString("dateOfBirth"));
            student.setPhone(resultSet.getString("phone"));
            student.setAddress(resultSet.getString("address"));
            return student;
        });
    }

    public void addStudent(Student student, int id) {
        String query = "insert into student (idGroup, firstName, middleName, lastName, dateOfBirth, address, phone) values (?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, id, student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getDateOfBirth(), student.getAddress(), student.getPhone());
    }

    public void updateStudent(Student student, int id) {
        String query = "update student set idGroup = ?, firstName = ?, middleName = ?, lastName = ?, dateOfBirth = ?, address = ?, phone = ? where idStudent = ?;";
        jdbcTemplate.update(query, id, student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getDateOfBirth(), student.getAddress(), student.getPhone(), student.getIdStudent());
    }

    public void deleteStudent(int id) {
        String query = "delete from student where idStudent = ?;";
        jdbcTemplate.update(query, id);
    }

    public StudentPerCategoryDTO getStudentsPerCategory(String cat) {
        String query = "select category.name, idStudent, student.idGroup, lastName, firstName, middleName, dateOfBirth, phone, address from student \n" +
                    "inner join mydb.groups on student.idGroup=mydb.groups.idGroup \n" +
                    "inner join category on mydb.groups.idCategory=category.idCategory\n" +
                    "where name = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {cat},(resultSet, i) -> {
                StudentPerCategoryDTO studentPerCategoryDTO = new StudentPerCategoryDTO();
                studentPerCategoryDTO.setCategoryName(resultSet.getString(1));
                studentPerCategoryDTO.setIdStudent(resultSet.getInt(2));
                studentPerCategoryDTO.setIdGroup(resultSet.getInt(3));
                studentPerCategoryDTO.setLastName(resultSet.getString(4));
                studentPerCategoryDTO.setFirstName(resultSet.getString(5));
                studentPerCategoryDTO.setMiddleName(resultSet.getString(6));
                studentPerCategoryDTO.setDateOfBirth(resultSet.getString(7));
                studentPerCategoryDTO.setPhone(resultSet.getString(8));
                studentPerCategoryDTO.setAddress(resultSet.getString(9));
        return studentPerCategoryDTO;
        });
    }

    public StudentDocumentsDTO getStudentDocuments(int id) {
        String query = "SELECT student.firstName,student.lastName, photo, mainDocumentsCopies, medicalСertificate from documents\n" +
                    "inner join student on documents.idStudent=student.idStudent\n" +
                    "where documents.idStudent = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id},(resultSet, i) -> {
            StudentDocumentsDTO studentDocumentsDTO = new StudentDocumentsDTO();
                studentDocumentsDTO.setFirstName(resultSet.getString(1));
                studentDocumentsDTO.setLastName(resultSet.getString(2));
                studentDocumentsDTO.setPhoto(resultSet.getString(3));
                studentDocumentsDTO.setMainDocumentsCopies(resultSet.getString(4));
                studentDocumentsDTO.setMedicalCertificate(resultSet.getString(5));
        return studentDocumentsDTO;
        });
    }

    public List<Student> getStudentsPerGroup(int id) {
        String query = "select * from student \n" +
                    "inner join mydb.groups on student.idGroup=mydb.groups.idGroup \n" +
                    "where mydb.groups.idGroup = ?;";
        return jdbcTemplate.query(query, new Object[] {id},(resultSet, i) -> {
            Student student = new Student();
            student.setIdStudent(resultSet.getInt("idStudent"));
            student.setGroup(getGroupForStudentDTO(resultSet.getInt("idGroup")));
            student.setFirstName(resultSet.getString("firstName"));
            student.setMiddleName(resultSet.getString("middleName"));
            student.setLastName(resultSet.getString("lastName"));
            student.setDateOfBirth(resultSet.getString("dateOfBirth"));
            student.setPhone(resultSet.getString("phone"));
            student.setAddress(resultSet.getString("address"));
            return student;
        });
    }

    public GroupForStudentDTO getGroupForStudentDTO(int id) {
        String query = "select group.groupNumber from mydb.group " +
                "inner join student on mydb.group.idGroup=student.idGroup " +
                "where mudb.group.idGroup = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            GroupForStudentDTO groupForStudentDTO = new GroupForStudentDTO();
            groupForStudentDTO.setGroupNumber(resultSet.getString("groupNumber"));
            return groupForStudentDTO;
        });
    }
}
