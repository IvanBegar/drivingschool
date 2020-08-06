package com.begar.demo.repository;

import com.begar.demo.dto.GroupDTO;
import com.begar.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TeacherRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Teacher> getTeachers() {
        String query = "select * from teacher;";
        return jdbcTemplate.query(query, getTeacherRowMapper());
    }

    public Teacher getTeacher(int id) {
        String query = "select * from teacher where teacher_id = ?;";
        return jdbcTemplate.queryForObject(query, getTeacherRowMapper(), id);
    }

    public void addTeacher(Teacher teacher) {
        String query = "insert into teacher (firstName, middleName, lastName, dateOfBirth, address, phone, salary) values (?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, teacher.getFirstName(), teacher.getMiddleName(), teacher.getLastName(), teacher.getDateOfBirth(), teacher.getAddress(), teacher.getPhone(), teacher.getSalary());
    }

    public void updateTeacher(Teacher teacher) {
        String query = "update teacher set firstName = ?, middleName = ?, lastName = ?, dateOfBirth = ?, adress = ?, phone = ?, salary = ? where teacher_id = ?;";
        jdbcTemplate.update(query, teacher.getFirstName(), teacher.getMiddleName(), teacher.getLastName(), teacher.getDateOfBirth(), teacher.getAddress(), teacher.getPhone(), teacher.getSalary(), teacher.getTeacher_id());
    }

    public void deleteTeacher(int id) {
        String query = "delete from teacher where teacher_id = ?;";
        jdbcTemplate.update(query, id);
    }

    public List<GroupDTO> getGroupDTO(int id) {
        String query = "select group.groupName from mydb.group " +
                "inner join teacher_group on group.group_id=teacher_group.group_id " +
                "inner join teacher on teacher_group.teacher_id=teacher.teacher_id " +
                "where teacher.teacher_id = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, (resultSet, i) -> {
            GroupDTO groupForStudentDTO = new GroupDTO();
            groupForStudentDTO.setGroupName(resultSet.getString("groupName"));
            return groupForStudentDTO;
        });
    }

    public void addTeacherToGroup(int id1, int id2) {
        String query = "insert into teacher_group (teacher_id, group_id) values (?, ?);";
        jdbcTemplate.update(query, id1, id2);
    }

    private RowMapper<Teacher> getTeacherRowMapper() {
        return (resultSet, i) -> {
            Teacher teacher = new Teacher();
            teacher.setTeacher_id(resultSet.getInt("teacher_id"));
            teacher.setFirstName(resultSet.getString("firstName"));
            teacher.setMiddleName(resultSet.getString("middleName"));
            teacher.setLastName(resultSet.getString("lastName"));
            teacher.setDateOfBirth(resultSet.getString("dateOfBirth"));
            teacher.setAddress(resultSet.getString("address"));
            teacher.setPhone(resultSet.getString("phone"));
            teacher.setSalary(resultSet.getDouble("salary"));
            teacher.setGroups(getGroupDTO(resultSet.getInt("teacher_id")));
            return teacher;
        };
    }
}

