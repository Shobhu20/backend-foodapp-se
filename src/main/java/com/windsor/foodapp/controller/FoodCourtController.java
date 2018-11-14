package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.model.FoodCourt;
import com.windsor.foodapp.model.FoodItem;
import com.windsor.foodapp.model.Restaurant;
import com.windsor.foodapp.service.FoodCourtService;
import com.windsor.foodapp.service.RestaurantService;
import com.windsor.foodapp.service.UserService;
import com.windsor.foodapp.util.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class FoodCourtController {

    @Resource
    FoodCourtService foodCourtService;

    @Resource
    RestaurantService restaurantService;

    ObjectMapper objectMapper = new ObjectMapper();

@RequestMapping(value = "/getFoodCourtByCity",method = RequestMethod.POST)

    public String getFoodCourt( @RequestParam("city") String city) throws Exception {

    Map<String, Object> resultMap = new HashMap<>();
    try {

    Map<String, Object> fcList = foodCourtService.getFoodCourtByCity(city);

    List<FoodCourt> foodCourts = (List<FoodCourt>) fcList.get("foodCourts");

    List<Object> foodCourtMap = new ArrayList<>();
    for (FoodCourt foodCourt : foodCourts) {
        Map<String, Object> foodCourtData = new HashMap<>();
        foodCourtData.put("id", String.valueOf(foodCourt.getId()));
        foodCourtData.put("foodCourtName", foodCourt.getFoodCourtName());
        foodCourtData.put("address", foodCourt.getAddress());
        foodCourtData.put("city", foodCourt.getCity());
        foodCourtData.put("open_time", foodCourt.getOpen_time());
        foodCourtData.put("close_time", foodCourt.getClose_time());
        List<Restaurant> restaurants = restaurantService.getRestaurantByFoodCourt(foodCourt.getId());

        List<Object> restaurantObject = new ArrayList<>();
        for(Restaurant restaurant: restaurants) {
            Map<String, String> data = new HashMap<>();
            data.put("id", String.valueOf(restaurant.getId()));
            data.put("name", restaurant.getName());
            data.put("fc_id", String.valueOf(restaurant.getFc_id()));
            data.put("iconName", restaurant.getIconUrl());
            restaurantObject.add(data);
        }

        foodCourtData.put("restaurants", restaurantObject);

        foodCourtMap.add(foodCourtData);
    }

        resultMap.put("status", "success");
        resultMap.put("fcList", foodCourtMap);

//    for(FoodCourt item: foodCourts) {
//
//
//
//        Map<String, Object> restaurants = new HashMap<>();
//        List<Restaurant> restaurantList= restaurantService.getRestaurantByFoodCourt(item.getId());
//        restaurants.put("Restaurants", restaurantList);
//        fcList.get("foodCourts");
//    }

//        resultMap.put("status", "success");
//        resultMap.put("fcList", (fcList));

        return objectMapper.writeValueAsString(resultMap);

    }
    catch (Exception e){
        resultMap.put("status", "failed");
        resultMap.put("reason", e.getMessage());
        return objectMapper.writeValueAsString(resultMap);
    }

}



}
