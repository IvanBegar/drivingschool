package com.begar.demo.repository;

import com.begar.demo.dto.CategoryForGroupDTO;
import com.begar.demo.dto.ScheduleForGroupDTO;
import com.begar.demo.dto.TeacherForGroupDTO;
import com.begar.demo.dto.VehicleForGroupDTO;
import com.begar.demo.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GroupRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Group> getGroups() {
        String query = "select * from mydb.group;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            Group group = new Group();
            group.setIdGroup(resultSet.getInt(1));
            group.setCategory(getCategoryForGroupDTO(resultSet.getInt(1)));
            group.setSchedule(getScheduleForGroupDTO(resultSet.getInt(1)));
            group.setGroupNumber(resultSet.getString(4));
            group.setStartDate(resultSet.getString(5));
            group.setEndDate(resultSet.getString(6));
            group.setTeachers(getTeacherForGroupDTO(resultSet.getInt(1)));
            group.setVehicles(getVehicleForGroupDTO(resultSet.getInt(1)));
            return group;
        });
    }

    public Group getGroup(int id) {
        String query = "select * from mydb.group where idGroup = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            Group group = new Group();
            group.setIdGroup(resultSet.getInt(1));
            group.setCategory(getCategoryForGroupDTO(resultSet.getInt(1)));
            group.setSchedule(getScheduleForGroupDTO(resultSet.getInt(1)));
            group.setGroupNumber(resultSet.getString(4));
            group.setStartDate(resultSet.getString(5));
            group.setEndDate(resultSet.getString(6));
            group.setTeachers(getTeacherForGroupDTO(resultSet.getInt(1)));
            group.setVehicles(getVehicleForGroupDTO(resultSet.getInt(1)));
            return group;
        });
    }

    public void addGroup(Group group, int id1, int id2) {
        String query = "insert into mydb.group (idCategory, idSchedule, groupNumber, startDate, endDate) values (?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, id1, id2, group.getGroupNumber(), group.getStartDate(), group.getEndDate());
    }

    public void updateGroup(Group group, int id1, int id2) {
        String query = "update mydb.group set idCategory = ?, idSchedule = ?, groupNumber = ?, startDate = ?, endDate = ? where idGroup = ?;";
        jdbcTemplate.update(query, id1, id2, group.getGroupNumber(), group.getStartDate(), group.getEndDate(), group.getIdGroup());
    }

    public void deleteGroup(int id) {
        String query = "delete from mydb.group where idGroup = ?;";
        jdbcTemplate.update(query, id);
    }

    public List<TeacherForGroupDTO> getTeacherForGroupDTO(int id) {
        String query = "select teacher.firstName, teacher.middleName, teacher.lastName, teacher.phone from teacher " +
                "inner join teacher_group on teacher.idTeacher=teacher_group.idTeacher " +
                "inner join mydb.group on teacher_group.idGroup=mydb.group.idGroup " +
                "where mydb.group.idGroup = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, (resultSet, i) -> {
            TeacherForGroupDTO teacherDTO = new TeacherForGroupDTO();
            teacherDTO.setFirstName(resultSet.getString("firstName"));
            teacherDTO.setMiddleName(resultSet.getString("middleName"));
            teacherDTO.setLastName(resultSet.getString("lastName"));
            teacherDTO.setPhone(resultSet.getString("phone"));
            return teacherDTO;
        });
    }

    public List<VehicleForGroupDTO> getVehicleForGroupDTO(int id) {
        String query = "select vehicle.autoBrand, vehicle.govNumber from vehicle " +
                "inner join group_vehicle on vehicle.idVehicle=group_vehicle.idVehicle " +
                "inner join mydb.group on group_vehicle.idGroup=group.idGroup " +
                "where mydb.group.idGroup = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, (resultSet, i) -> {
            VehicleForGroupDTO vehicleForGroupDTO = new VehicleForGroupDTO();
            vehicleForGroupDTO.setAutoBrand(resultSet.getString("autoBrand"));
            vehicleForGroupDTO.setGovNumber(resultSet.getString("govNumber"));
            return vehicleForGroupDTO;
        });
    }

    public CategoryForGroupDTO getCategoryForGroupDTO(int id) {
        String query = "select category.name from mydb.group " +
                "inner join category on group.idCategory=category.idCategory " +
                "where group.idGroup = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            CategoryForGroupDTO categoryForGroupDTO = new CategoryForGroupDTO();
            categoryForGroupDTO.setCategoryName(resultSet.getString("name"));
            return categoryForGroupDTO;
        });
    }

    public ScheduleForGroupDTO getScheduleForGroupDTO(int id) {
        String query = "select name, scheduleDescription from mydb.group " +
                "inner join schedule on group.idSchedule=schedule.idSchedule " +
                "where group.idGroup = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            ScheduleForGroupDTO scheduleForGroupDTO = new ScheduleForGroupDTO();
            scheduleForGroupDTO.setName(resultSet.getString("name"));
            scheduleForGroupDTO.setScheduleDescription(resultSet.getString("scheduleDescription"));
            return scheduleForGroupDTO;
        });
    }
}
