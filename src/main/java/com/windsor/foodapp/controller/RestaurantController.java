package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;
import com.windsor.foodapp.model.Restaurant;
import com.windsor.foodapp.service.RestaurantService;
import com.windsor.foodapp.service.UserService;
import com.windsor.foodapp.util.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    @Resource
    RestaurantService restaurantService;



    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/Restaurant")
    public String getRestaurant(@RequestParam("fc_id") int fc_id) throws Exception {

        Map<String,Object> resultMap = new HashMap<>();

        try {
        List<Restaurant> restaurantList= restaurantService.getRestaurantByFoodCourt(fc_id);

            resultMap.put("status","success");
            resultMap.put("restaurantList",restaurantList);
            return objectMapper.writeValueAsString(resultMap);
        }
        catch(Exception e) {
            resultMap.put("status","failed");
            resultMap.put("reason",e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }

    }



}
