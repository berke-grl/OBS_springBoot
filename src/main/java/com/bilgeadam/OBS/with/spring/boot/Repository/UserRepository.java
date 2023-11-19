package com.bilgeadam.OBS.with.spring.boot.Repository;

import com.bilgeadam.OBS.with.spring.boot.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByEmail(String email) {
        String sql = "Select * From \"users\" Where \"email\" = :searchedMail";
        Map<String, String> param = new HashMap<>();
        param.put("searchedMail", email);

        List<User> users = jdbcTemplate.query(sql, param, BeanPropertyRowMapper.newInstance(User.class));
        if(users.isEmpty())
            return null;
        else
            return users.get(0);
    }

    public boolean save(User user){
        String sql = "Insert Into \"users\"(\"email\", \"password\", \"role\") Values( :newEmail, :newPassword, :newRole)";
        Map<String,Object> params = new HashMap<>();
        params.put("newEmail", user.getEmail());
        params.put("newPassword", user.getPassword());
        params.put("newRole", user.getRole());

        return jdbcTemplate.update(sql,params) == 1;
    }
}













