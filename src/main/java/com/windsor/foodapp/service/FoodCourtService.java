package com.windsor.foodapp.service;

import com.windsor.foodapp.dao.FoodCourtDao;
import com.windsor.foodapp.model.FoodCourt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FoodCourtService {

    @Resource
    FoodCourtDao foodCourtDao;

    public List<FoodCourt> getFoodCourtByCity(String city) throws  Exception{

        return  foodCourtDao.getFoodCourtByCity(city);
    }
}
