package com.begar.demo.repository;

import com.begar.demo.dto.TeacherForGroupDTO;
import com.begar.demo.dto.VehicleDTO;
import com.begar.demo.entity.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GroupRepository {

    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GroupRepository(CategoryRepository categoryRepository,
                           ScheduleRepository scheduleRepository,
                           JdbcTemplate jdbcTemplate,
                           NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.categoryRepository = categoryRepository;
        this.scheduleRepository = scheduleRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Group> getGroups() {
        String query = "select * from mydb.group;";
        return jdbcTemplate.query(query, getGroupRowMapper());
    }

    public Group getGroup(int id) {
        String query = "select * from mydb.group where group_id = ?;";
        return jdbcTemplate.queryForObject(query, getGroupRowMapper(), id);
    }

    public void addGroup(Group group, int id1, int id2) {
        String query = "insert into mydb.group (category_id, schedule_id, groupName, startDate, endDate) values (:category_id, :schedule_id, :groupName, :startDate, :endDate);";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("category_id", id1)
                .addValue("schedule_id", id2)
                .addValue("groupName", group.getGroupName())
                .addValue("startDate", group.getStartDate())
                .addValue("endDate", group.getEndDate());
        namedParameterJdbcTemplate.update(query, sqlParameterSource);
    }

    public void updateGroup(Group group, int id1, int id2) {
        String query = "update mydb.group set category_id = ?, schedule_id = ?, groupName = ?, startDate = ?, endDate = ? where group_id = ?;";
        jdbcTemplate.update(query, id1, id2, group.getGroupName(), group.getStartDate(), group.getEndDate(), group.getGroup_id());
    }

    public void deleteGroup(int id) {
        String query = "delete from mydb.group where id_group = ?;";
        jdbcTemplate.update(query, id);
    }

    public List<TeacherForGroupDTO> getTeacherForGroupDTO(int id) {
        String query = "select teacher.firstName, teacher.middleName, teacher.lastName, teacher.phone from teacher " +
                "inner join teacher_group on teacher.teacher_id=teacher_group.teacher_id " +
                "inner join mydb.group on teacher_group.group_id=mydb.group.group_id " +
                "where mydb.group.group_id = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, (resultSet, i) -> {
            TeacherForGroupDTO teacherDTO = new TeacherForGroupDTO();
            teacherDTO.setFirstName(resultSet.getString("firstName"));
            teacherDTO.setMiddleName(resultSet.getString("middleName"));
            teacherDTO.setLastName(resultSet.getString("lastName"));
            teacherDTO.setPhone(resultSet.getString("phone"));
            return teacherDTO;
        });
    }

    public List<VehicleDTO> getVehicleDTO(int id) {
        String query = "select vehicle.autoBrand, vehicle.govNumber from vehicle " +
                "inner join group_vehicle on vehicle.vehicle_id=group_vehicle.vehicle_id " +
                "inner join mydb.group on group_vehicle.group_id=mydb.group.group_id " +
                "where mydb.group.group_id = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, (resultSet, i) -> {
            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleDTO.setAutoBrand(resultSet.getString("autoBrand"));
            vehicleDTO.setGovNumber(resultSet.getString("govNumber"));
            return vehicleDTO;
        });
    }

    private RowMapper<Group> getGroupRowMapper() {
        return (resultSet, i) -> {
            Group group = new Group();
            group.setGroup_id(resultSet.getInt("group_id"));
            group.setCategory(categoryRepository.getCategory(resultSet.getInt("group_id")));
            group.setSchedule(scheduleRepository.getSchedule(resultSet.getInt("group_id")));
            group.setGroupName(resultSet.getString("groupName"));
            group.setStartDate(resultSet.getString("startDate"));
            group.setEndDate(resultSet.getString("endDate"));
            group.setTeachers(getTeacherForGroupDTO(resultSet.getInt("group_id")));
            group.setVehicles(getVehicleDTO(resultSet.getInt("group_id")));
            return group;
        };
    }
}
