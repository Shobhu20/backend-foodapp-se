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

    public List<Restaurant> getRestaurantByFoodCourt(int fc_id) throws Exception {
        List<Restaurant> restaurantList = new ArrayList<>();
        List<Map<String, Object>> stringObjectMap = jdbcTemplate.queryForList("select r.* from restaurant r inner join foodcourt fc on fc.id = r.fc_id where fc.id = ?", fc_id);
        if (stringObjectMap.isEmpty())
            throw new Exception("No Restaurant found");
        for (Map<String, Object> result : stringObjectMap) {
            Restaurant restaurant = new Restaurant(Integer.parseInt(result.get("id").toString()),result.get("name").toString(), result.get("URL").toString(), Integer.parseInt(result.get("fc_id").toString()));
            restaurantList.add(restaurant);
        }
        return restaurantList;

    }
}
