package com.windsor.foodapp.service;

import com.windsor.foodapp.dao.FoodCourtDao;
import com.windsor.foodapp.model.FoodCourt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class FoodCourtService {

    @Resource
    FoodCourtDao foodCourtDao;

    public Map<String, Object> getFoodCourtByCity(String city) throws  Exception{

        return  foodCourtDao.getFoodCourtByCity(city);
    }
}
