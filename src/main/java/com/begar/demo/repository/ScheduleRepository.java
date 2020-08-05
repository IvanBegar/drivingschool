package com.begar.demo.repository;

import com.begar.demo.dto.SchedulesPerGroupsDTO;
import com.begar.demo.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {

    @Autowired
    private DataSource dataSource;

    public List<Schedule> getSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from schedule;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setIdSchedule(resultSet.getInt(1));
                schedule.setName(resultSet.getString(2));
                schedule.setScheduleDescription(resultSet.getString(3));
                schedules.add(schedule);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public Schedule getSchedule(int id) {
        Schedule schedule = new Schedule();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from schedule where idSchedule = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                schedule.setIdSchedule(resultSet.getInt(1));
                schedule.setName(resultSet.getString(2));
                schedule.setScheduleDescription(resultSet.getString(3));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    public void addSchedule(Schedule schedule) {
        try {
            Connection con = dataSource.getConnection();
            String query = "insert into schedule (name, scheduleDescription) values (?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, schedule.getName());
            preparedStatement.setString(2, schedule.getScheduleDescription());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSchedule(Schedule schedule) {
        try {
            Connection con = dataSource.getConnection();
            String query = "update schedule set name = ?, scheduleDescription = ? where idSchedule = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, schedule.getName());
            preparedStatement.setString(2, schedule.getScheduleDescription());
            preparedStatement.setDouble(3,schedule.getIdSchedule());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSchedule(int id) {
        try {
            Connection con = dataSource.getConnection();
            String query = "delete from schedule where idSchedule = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SchedulesPerGroupsDTO> getSchedulesPerGroups() {
        List<SchedulesPerGroupsDTO> schedulesPerGroupsDTOs = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT groups.groupNumber, name, scheduleDescription FROM schedule\n" +
                    "inner join mydb.groups on schedule.idSchedule=groups.idSchedule;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                SchedulesPerGroupsDTO schedulesPerGroupsDTO = new SchedulesPerGroupsDTO();
                schedulesPerGroupsDTO.setGroupNumber(resultSet.getString(1));
                schedulesPerGroupsDTO.setScheduleName(resultSet.getString(2));
                schedulesPerGroupsDTO.setScheduleDescription(resultSet.getString(3));
                schedulesPerGroupsDTOs.add(schedulesPerGroupsDTO);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedulesPerGroupsDTOs;
    }
}
