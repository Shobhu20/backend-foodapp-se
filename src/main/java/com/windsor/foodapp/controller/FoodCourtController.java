package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.model.FoodCourt;
import com.windsor.foodapp.service.FoodCourtService;
import com.windsor.foodapp.service.UserService;
import com.windsor.foodapp.util.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class FoodCourtController {

    @Resource
    FoodCourtService foodCourtService;

    ObjectMapper objectMapper = new ObjectMapper();

@RequestMapping(value = "/getFoodCourtByCity",method = RequestMethod.POST)

    public String getFoodCourt( @RequestParam("city") String city) throws Exception {

    Map<String, Object> resultMap = new HashMap<>();

    List<FoodCourt> fcList=foodCourtService.getFoodCourtByCity(city);
    try {
        resultMap.put("status", "success");
        resultMap.put("fcList", (fcList));
        return objectMapper.writeValueAsString(resultMap);

    }
    catch (Exception e){
        resultMap.put("status", "failed");
        resultMap.put("reason", e.getMessage());
        return objectMapper.writeValueAsString(resultMap);
    }

}



}
