package com.begar.demo.repository;

import com.begar.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addNewUser(User user) {
        String query1 = "insert into users (username, password) values (?, ?);";
        String query2 = "insert into user_roles (username, role) values (?, ?);";
        jdbcTemplate.update(query1, user.getUsername(), user.getPassword());
        jdbcTemplate.update(query2, user.getUsername(), user.getRole());
    }
}
