package com.begar.demo.repository;

import com.begar.demo.dto.GroupForVehicleDTO;
import com.begar.demo.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VehicleRepository {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Vehicle> getVehicles() {
        String query = "select * from vehicle;";
        return jdbcTemplate.query(query, getVehicleRowMapper());
    }

    public Vehicle getVehicle(int id) {
        String query = "select * from vehicle where vehicle_id = ?;";
        return jdbcTemplate.queryForObject(query, getVehicleRowMapper(), id);
    }

    public void addVehicle(Vehicle vehicle) {
        String query = "insert into vehicle (autoBrand, govNumber, year) values (?, ?, ?);";
        jdbcTemplate.update(query, vehicle.getAutoBrand(), vehicle.getGovNumber(), vehicle.getYear());
    }

    public void updateVehicle(Vehicle vehicle) {
        String query = "update vehicle set autoBrand = ?, govNumber = ?, year = ? where vehicle_id = ?;";
        jdbcTemplate.update(query, vehicle.getAutoBrand(), vehicle.getGovNumber(), vehicle.getYear(), vehicle.getVehicle_id());
    }

    public void deleteVehicle(int id) {
        String query = "delete from vehicle where vehicle_id = ?;";
        jdbcTemplate.update(query, id);
    }

    public List<GroupForVehicleDTO> getGroupForVehicleDTO(int id) {
        String query = "select group.groupName, category.name, schedule.description from mydb.group " +
                    "inner join group_vehicle on group.group_id=group_vehicle.group_id " +
                    "inner join category on group.category_id=category.category_id " +
                    "inner join schedule on group.schedule_id=schedule.schedule_id " +
                    "inner join vehicle on group_vehicle.vehicle_id=vehicle.vehicle_id " +
                    "where vehicle.vehicle_id = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, (resultSet, i) -> {
            GroupForVehicleDTO groupDTO = new GroupForVehicleDTO();
            groupDTO.setGroupName(resultSet.getString("groupName"));
            groupDTO.setCategoryName(resultSet.getString("name"));
            groupDTO.setScheduleDescription(resultSet.getString("description"));
            return groupDTO;
        });
    }

    public void addGroupToVehicle(int id1, int id2) {
        String query = "insert into group_vehicle (group_id, vehicle_id) values (?, ?);";
        jdbcTemplate.update(query, id1, id2);
    }

    private RowMapper<Vehicle> getVehicleRowMapper() {
        return (resultSet, i) -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicle_id(resultSet.getInt("vehicle_id"));
            vehicle.setGovNumber(resultSet.getString("govNumber"));
            vehicle.setAutoBrand(resultSet.getString("autoBrand"));
            vehicle.setYear(resultSet.getString("year"));
            vehicle.setGroups(teacherRepository.getGroupDTO(resultSet.getInt("vehicle_id")));
            return vehicle;
        };
    }
}

