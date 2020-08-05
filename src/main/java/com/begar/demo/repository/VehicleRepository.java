package com.begar.demo.repository;

import com.begar.demo.dto.GroupForVehicleDTO;
import com.begar.demo.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VehicleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Vehicle> getVehicles(){
        String query = "select * from vehicle;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setIdVehicle(resultSet.getInt("idVehicle"));
            vehicle.setGovNumber(resultSet.getString("govNumber"));
            vehicle.setAutoBrand(resultSet.getString("autoBrand"));
            vehicle.setYear(resultSet.getString("year"));
            vehicle.setGroups(getGroupForVehicleDTO(resultSet.getInt("idVehicle")));
            return vehicle;
        });
    }

    public Vehicle getVehicle(int id) {
        String query = "select * from vehicle where idVehicle = ?;";
        return jdbcTemplate.queryForObject(query, new Object[] {id}, (resultSet, i) -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setIdVehicle(resultSet.getInt("idVehicle"));
            vehicle.setGovNumber(resultSet.getString("govNumber"));
            vehicle.setAutoBrand(resultSet.getString("autoBrand"));
            vehicle.setYear(resultSet.getString("year"));
            vehicle.setGroups(getGroupForVehicleDTO(resultSet.getInt("idVehicle")));
            return vehicle;
        });
    }

    public void addVehicle(Vehicle vehicle) {
        String query = "insert into vehicle (autoBrand, govNumber, year) values (?, ?, ?);";
        jdbcTemplate.update(query, vehicle.getAutoBrand(), vehicle.getGovNumber(), vehicle.getYear());
    }

    public void updateVehicle(Vehicle vehicle) {
        String query = "update vehicle set autoBrand = ?, govNumber = ?, year = ? where idVehicle = ?;";
        jdbcTemplate.update(query, vehicle.getAutoBrand(), vehicle.getGovNumber(), vehicle.getYear(), vehicle.getIdVehicle());
    }

    public void deleteVehicle(int id) {
        String query = "delete from vehicle where idVehicle = ?;";
        jdbcTemplate.update(query, id);
    }

    public List<GroupForVehicleDTO> getGroupForVehicleDTO(int id) {
        String query = "select group.groupNumber, category.name, schedule.scheduleDescription from mydb.group " +
                    "inner join group_vehicle on group.idGroup=group_vehicle.idGroup " +
                    "inner join category on group.idCategory=category.idCategory " +
                    "inner join schedule on group.idSchedule=schedule.idSchedule " +
                    "inner join vehicle on group_vehicle.idVehicle=vehicle.idVehicle " +
                    "where vehicle.idVehicle = ?;";
        return jdbcTemplate.query(query, new Object[] {id}, (resultSet, i) -> {
            GroupForVehicleDTO groupDTO = new GroupForVehicleDTO();
            groupDTO.setGroupNumber(resultSet.getString("groupNumber"));
            groupDTO.setCategoryName(resultSet.getString("category.name"));
            groupDTO.setScheduleDescription(resultSet.getString("scheduleDescription"));
            return groupDTO;
        });
    }

    public void addGroupToVehicle(int id1, int id2) {
        String query = "insert into group_vehicle (idGroup, idVehicle) values (?, ?);";
        jdbcTemplate.update(query, id1, id2);
    }
}

