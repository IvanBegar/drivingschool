package com.begar.demo.repository;

import com.begar.demo.entity.Group;
import com.begar.demo.entity.User;
import com.begar.demo.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addNewUser(User user) {
        String query1 = "insert into users (username, password, phone) values (?, ?, ?);";
        String query2 = "insert into user_roles (username, role) values (?, ?);";
        jdbcTemplate.update(query1, user.getUsername(), user.getPassword(), user.getPhone());
        jdbcTemplate.update(query2, user.getUsername(), user.getRole());
    }

    public User getUserByName(String username) throws DataException {
        try {
            String query = "select * from users inner join user_roles on users.username=user_roles.username where users.username = ?;";
            return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setPhone(resultSet.getString("phone"));
                return user;
            }, username);
        } catch (EmptyResultDataAccessException e) {
            User emptyUser = new User();
            return emptyUser;
        }
    }
}
