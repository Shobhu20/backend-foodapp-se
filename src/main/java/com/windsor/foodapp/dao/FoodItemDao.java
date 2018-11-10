package com.windsor.foodapp.dao;

import com.windsor.foodapp.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FoodItemDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<FoodItem> getFoodItemByRestaurant(String r_name) throws Exception {
        List<FoodItem> foodItemList = new ArrayList<>();
        List<Map<String, Object>> stringObjectMap = jdbcTemplate.queryForList("select f.* from fooditem f inner join restaurant r on r.id = f.id where r_name= ?", r_name);
        if (stringObjectMap.isEmpty())
            throw new Exception("No such Food Item found");
        for (Map<String, Object> result : stringObjectMap) {
            FoodItem foodItem = new FoodItem(Integer.parseInt(result.get("id").toString()),
                    result.get("r_name").toString(),
                    Double.parseDouble(result.get("cost").toString()),
                    result.get("category").toString(),
                    result.get("preptime").toString(),
                    Integer.parseInt(result.get("r_id").toString()) );
            foodItemList.add(foodItem);
        }
        return foodItemList;

    }
}



