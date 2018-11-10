package com.windsor.foodapp.dao;

import com.windsor.foodapp.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
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
                    result.get("name").toString(),
                    Double.parseDouble(result.get("cost").toString()),
                    result.get("category").toString(),
                    Integer.parseInt(result.get("r_id").toString()),
                            Integer.parseInt(result.get("fc_id").toString()),

                            Long.parseLong(result.get("preptime").toString()),
                            result.get("restaurant_name").toString(),
                            result.get("foodcourt_name").toString());
            foodItemList.add(foodItem);
        }
        return foodItemList;

    }


    public Map<Integer, FoodItem> getFoodItemsForIds(String ids) {
        Map<Integer, FoodItem> returnMap = new HashMap<>();
        String sql = "select * from fooditem where id in (" + ids + ")";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        for(Map<String, Object> result : resultList) {
            returnMap.put(Integer.parseInt(result.get("id").toString()), convertSQLResultToFoodItem(result));
        }
        return returnMap;
    }

    private FoodItem convertSQLResultToFoodItem(Map<String, Object> result) {
        //todo : convert to item
        return null;
    }
}
