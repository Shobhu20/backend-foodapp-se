package com.windsor.foodapp.dao;

import com.windsor.foodapp.model.FoodItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FoodItemDao {

    @Resource
    JdbcTemplate jdbcTemplate;

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
