package com.windsor.foodapp.dao;

import ch.qos.logback.core.net.server.Client;
import com.windsor.foodapp.enums.CLIENT_ROLE;
import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;
import com.windsor.foodapp.model.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

@Component
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public Map<String,Object> authenticateUserAndGetToken(String email, String password) throws Exception {
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM APPUSER WHERE EMAIL_ID = ?", email);
        if(resultList.isEmpty())
            throw new Exception("user not found");
        Map<String, Object>  result = resultList.get(0);
        return  result;
    }

    public void registerUser(ClientUser clientUser) throws Exception {
        String creation_date = LocalDateTime.now().toString();
          String sql = "insert into AppUser (first_name,last_name,email_id, password,phone_number,user_status,created_at,updated_at) " +
                  "values ('" + clientUser.getFirstName() + ("','") + clientUser.getLastName() + ("','") + clientUser.getEmail()  + ("','") + clientUser.getPassword()  + ("','") + clientUser.getPhoneNumber() + "','" + clientUser.getStatus().getValue() + ("','") + creation_date+("','") + creation_date+("')");
          try {
              jdbcTemplate.update(sql);
          } catch (Exception e) {
              if(e.getMessage().contains("duplicate key value violates unique constraint "))
                  throw new Exception("email/phone number already exists");
              throw new Exception(e.getMessage());
          }
    }

    public String getTokenForEmail(String email) {
        String sql = "select token from authtoken where email = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, email);
        if(maps.isEmpty())
            return null;
        return maps.get(0).get("token").toString();
    }

    public String getNameForEmail(String email){
        String sql= "select first_name from authtoken where email =?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, email);
        if(maps.isEmpty())
            return null;
        return maps.get(0).get("first_name").toString();
    }

    public void saveTokenForEmail(String email, String token) {
        String sql = "INSERT INTO authtoken (token, email, created_at) VALUES ('" + token +"','" + email + "','" + LocalDate.now().toString() + "')";
        jdbcTemplate.update(sql);
    }

    public ClientUser getUserForEmail(String email) {
        String sql = "SELECT * FROM AppUser WHERE email_id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, email);
        return new ClientUser(Integer.parseInt(result.get("id").toString()),result.get("email_id").toString() , "N/A", result.get("first_name").toString(),result.get("last_name").toString(),result.get("phone_number").toString(), CLIENT_STATUS_ENUM.getByValue(Integer.parseInt(result.get("user_status").toString())), CLIENT_ROLE.getByValue(Integer.parseInt(result.get("user_role").toString())));
    }

    public void logout(String email,String token) throws Exception {
        String sql = "DELETE FROM AuthToken WHERE email = ?";

        try{jdbcTemplate.update(sql,email);
        } catch (Exception e){
            throw new  Exception(e.getMessage());
        }

    }

    public void updateProfile(String email, String firstName, String lastName, String phoneNum, String password) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update AppUser set");
        try {
            if(firstName != null)
                sql.append(" first_name ='" + firstName + "',");
            if(lastName != null)
                sql.append(" last_name ='" + lastName + "',");
            if(phoneNum != null)
                sql.append(" phone_number ='" + phoneNum + "',");
            if(password != null)
                sql.append(" password ='" + password + "',");
            String sqlUpdateQuery = sql.substring(0, sql.length()-1);
            sqlUpdateQuery = sqlUpdateQuery + " where email_id = '" + email + "'";
            jdbcTemplate.update(sqlUpdateQuery);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }
}
