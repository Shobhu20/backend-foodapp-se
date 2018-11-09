package com.windsor.foodapp.dao;

import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.model.FoodCourt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FoodCourtDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<FoodCourt> getFoodCourtByCity( String city) throws  Exception{
        List<FoodCourt> foodCourtList = new ArrayList<>();
        List<Map<String, Object>> stringObjectMap = jdbcTemplate.queryForList("SELECT * FROM FoodCourt WHERE city = ?", city);
        if(stringObjectMap.isEmpty())
            throw new Exception("No Food Court found");
        for(Map<String, Object> result : stringObjectMap) {
            FoodCourt fc = new FoodCourt(result.get("name").toString(), result.get("address").toString(), result.get("city").toString(), result.get("open_time").toString(), result.get("close_time").toString());
            foodCourtList.add(fc);
        }
        return foodCourtList;

    }

}
