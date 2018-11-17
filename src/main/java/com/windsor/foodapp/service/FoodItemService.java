package com.windsor.foodapp.service;


import com.windsor.foodapp.dao.FoodItemDao;
import com.windsor.foodapp.model.FoodItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FoodItemService {

    @Resource
    FoodItemDao foodItemDao;

    public Map<String, List<FoodItem>> getFoodItemByRestaurant(int r_id) throws Exception {
        return foodItemDao.getFoodItemByRestaurant(r_id);

    }

}
