package com.begar.demo.repository;

import com.begar.demo.dto.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ScheduleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Schedule> getSchedules() {
        String query = "select * from schedule;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            Schedule schedule = new Schedule();
            schedule.setIdSchedule(resultSet.getInt("idSchedule"));
            schedule.setName(resultSet.getString("name"));
            schedule.setScheduleDescription(resultSet.getString("scheduleDescription"));
            return schedule;
        });
    }

    public Schedule getSchedule(int id) {
        String query = "select * from schedule where idSchedule = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id},(resultSet, i) -> {
            Schedule schedule = new Schedule();
            schedule.setIdSchedule(resultSet.getInt("idSchedule"));
            schedule.setName(resultSet.getString("name"));
            schedule.setScheduleDescription(resultSet.getString("scheduleDescription"));
            return schedule;
        });
    }

    public void addSchedule(Schedule schedule) {
        String query = "insert into schedule (name, scheduleDescription) values (?, ?);";
        jdbcTemplate.update(query, schedule.getName(), schedule.getScheduleDescription());
    }

    public void updateSchedule(Schedule schedule) {
        String query = "update schedule set name = ?, scheduleDescription = ? where idSchedule = ?;";
        jdbcTemplate.update(query, schedule.getName(), schedule.getScheduleDescription(), schedule.getIdSchedule());
    }

    public void deleteSchedule(int id) {
        String query = "delete from schedule where idSchedule = ?;";
        jdbcTemplate.update(query, id);
    }

    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        String query = "SELECT groups.groupNumber, name, scheduleDescription FROM schedule\n" +
                "inner join mydb.groups on schedule.idSchedule=groups.idSchedule;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            SchedulesPerGroupsDTO schedulesPerGroupsDTO = new SchedulesPerGroupsDTO();
            schedulesPerGroupsDTO.setGroupNumber(resultSet.getString("groupNumber"));
            schedulesPerGroupsDTO.setScheduleName(resultSet.getString("name"));
            schedulesPerGroupsDTO.setScheduleDescription(resultSet.getString("scheduleDescription"));
            return schedulesPerGroupsDTO;
        });
    }
}
