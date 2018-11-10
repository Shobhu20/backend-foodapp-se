package com.windsor.foodapp.service;


import com.windsor.foodapp.dao.FoodCourtDao;
import com.windsor.foodapp.dao.FoodItemDao;
import com.windsor.foodapp.model.FoodCourt;
import com.windsor.foodapp.model.FoodItem;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodItemListService {

    @Resource
    FoodItemDao foodItemDao;

    public List<FoodItem> getFoodItemByRestaurant(String r_name) throws Exception {
        return foodItemDao.getFoodItemByRestaurant(r_name);

    }

}
