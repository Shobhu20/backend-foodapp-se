package com.windsor.foodapp.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.foodapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Resource
    UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    //todo : post request
    //todo : add logging
    @RequestMapping("/home")
    public String getHome(@RequestParam("email") String email, @RequestParam("password") String password) throws JsonProcessingException {
        Map<String, String> resultMap = new HashMap<>();

        try {
            String token = userService.authenticateUserAndGetToken(email, password);
            resultMap.put("status", "success");
            resultMap.put("token", token);
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }
    }
}
