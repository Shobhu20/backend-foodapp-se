package com.windsor.foodapp.dao;

import com.windsor.foodapp.model.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

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

    public void registerUser(ClientUser clientUser) throws Exception {
        //TODO : insert creation and updation date
        String creation_date = LocalDateTime.now().toString();

        String sql = "insert into AppUser (first_name,last_name,email_id, password,phone_number,user_status,created_at,updated_at) " +
                "values ('" + clientUser.getFirstName() + ("','") + clientUser.getLastName() + ("','") + clientUser.getEmail()  + ("','") + clientUser.getPassword()  + ("','") + clientUser.getPhoneNumber() + "','" + clientUser.getStatus().getValue() + ("','") + creation_date+("','") + creation_date+("')");
        try {
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            if(e.getMessage().contains("duplicate key value violates unique constraint \"con_first\""))
                throw new Exception("email already exists");
            throw new Exception(e.getMessage());
        }
    }
}
