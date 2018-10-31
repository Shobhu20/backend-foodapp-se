package com.windsor.foodapp.foodapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Component
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public Boolean authenticateUserAndGetToken(String email, String password) throws Exception {
        List<Map<String, Object>> stringObjectMap = jdbcTemplate.queryForList("SELECT * FROM APPUSER WHERE EMAIL_ID = ?", email);
        if(stringObjectMap.isEmpty())
            throw new Exception("user not found");
        if(stringObjectMap.get(0).get("password").equals(password))
            return true;
        return false;
    }
}
