package com.begar.demo.repository;

import com.begar.demo.dto.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ScheduleRepository {

    public static final RowMapper<Schedule> SCHEDULE_ROW_MAPPER = (resultSet, i) -> {
        Schedule schedule = new Schedule();
        schedule.setSchedule_id(resultSet.getInt("schedule_id"));
        schedule.setName(resultSet.getString("name"));
        schedule.setDescription(resultSet.getString("description"));
        return schedule;
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Schedule> getSchedules() {
        String query = "select * from schedule;";
        return jdbcTemplate.query(query, SCHEDULE_ROW_MAPPER);
    }

    public Schedule getSchedule(int id) {
        String query = "select * from schedule where schedule_id = ?;";
        return jdbcTemplate.queryForObject(query, SCHEDULE_ROW_MAPPER, id);
    }

    public void addSchedule(Schedule schedule) {
        String query = "insert into schedule (name, description) values (?, ?);";
        jdbcTemplate.update(query, schedule.getName(), schedule.getDescription());
    }

    public void updateSchedule(Schedule schedule) {
        String query = "update schedule set name = ?, description = ? where schedule_id = ?;";
        jdbcTemplate.update(query, schedule.getName(), schedule.getDescription(), schedule.getSchedule_id());
    }

    public void deleteSchedule(int id) {
        String query = "delete from schedule where schedule_id = ?;";
        jdbcTemplate.update(query, id);
    }

    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        String query = "SELECT group.groupName, name, description FROM schedule\n" +
                "inner join mydb.group on schedule.schedule_id=groups.schedule_id;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            SchedulesPerGroupsDTO schedulesPerGroupsDTO = new SchedulesPerGroupsDTO();
            schedulesPerGroupsDTO.setGroupNumber(resultSet.getString("groupName"));
            schedulesPerGroupsDTO.setScheduleName(resultSet.getString("name"));
            schedulesPerGroupsDTO.setDescription(resultSet.getString("description"));
            return schedulesPerGroupsDTO;
        });
    }
}
