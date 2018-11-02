package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

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

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String registerUser(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("phoneNumber") String phoneNumber) throws JsonProcessingException {

        Map<String, String> resultMap = new HashMap<>();


        ClientUser clientUser = new ClientUser(email, password, firstName, lastName, phoneNumber, CLIENT_STATUS_ENUM.ACTIVE);
/*        try {
         validateUserFields(clientUser);
         }
           catch(Exception e){
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }*/
        try {
        userService.registerUser(clientUser);
        resultMap.put("status", "success");
        return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            //TODO : get error message incase of duplicate email and give that instead
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }
    }
}
