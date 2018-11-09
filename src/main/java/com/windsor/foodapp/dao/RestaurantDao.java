package com.windsor.foodapp.dao;

import com.windsor.foodapp.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RestaurantDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Restaurant> getRestaurantByFoodCourt(String fcName) throws Exception {
        List<Restaurant> restaurantList = new ArrayList<>();
        List<Map<String, Object>> stringObjectMap = jdbcTemplate.queryForList("select r.* from restaurant r inner join foodcourt fc on fc.id = r.fc_id where fc.name = ?", fcName);
        if (stringObjectMap.isEmpty())
            throw new Exception("No Restaurant found");
        for (Map<String, Object> result : stringObjectMap) {
            Restaurant restaurant = new Restaurant(result.get("name").toString(), result.get("URL").toString());
            restaurantList.add(restaurant);
        }
        return restaurantList;

    }
}
