package com.begar.demo.repository;

import com.begar.demo.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {

    @Autowired
    private DataSource dataSource;

    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from mydb.groups;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Group group = new Group();
                group.setIdGroup(resultSet.getInt(1));
                group.setIdCategory(resultSet.getInt(2));
                group.setIdSchedule(resultSet.getInt(3));
                group.setGroupNumber(resultSet.getString(4));
                group.setStartDate(resultSet.getString(5));
                group.setEndDate(resultSet.getString(6));
                groups.add(group);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public Group getGroup(int id) {
        Group group = new Group();
        try {
            Connection con = dataSource.getConnection();
            String query = "select * from mydb.groups where idGroup = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                group.setIdGroup(resultSet.getInt(1));
                group.setIdCategory(resultSet.getInt(2));
                group.setIdSchedule(resultSet.getInt(3));
                group.setGroupNumber(resultSet.getString(4));
                group.setStartDate(resultSet.getString(5));
                group.setEndDate(resultSet.getString(6));
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public void addGroup(Group group) {
        try {
            Connection con = dataSource.getConnection();
            String query = "insert into mydb.groups (idCategory, idSchedule, groupNumber, startDate, endDate) values (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, group.getIdCategory());
            preparedStatement.setInt(2, group.getIdSchedule());
            preparedStatement.setString(3, group.getGroupNumber());
            preparedStatement.setString(4, group.getStartDate());
            preparedStatement.setString(5, group.getEndDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(Group group) {
        try {
            Connection con = dataSource.getConnection();
            String query = "update mydb.groups set idCategory = ?, idSchedule = ?, groupNumber = ?, startDate = ?, endDate = ? where idGroup = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, group.getIdCategory());
            preparedStatement.setInt(2, group.getIdSchedule());
            preparedStatement.setString(3, group.getGroupNumber());
            preparedStatement.setString(4, group.getStartDate());
            preparedStatement.setString(5, group.getEndDate());
            preparedStatement.setInt(6, group.getIdGroup());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(int id) {
        try {
            Connection con = dataSource.getConnection();
            String query = "delete from mydb.groups where idGroup = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
