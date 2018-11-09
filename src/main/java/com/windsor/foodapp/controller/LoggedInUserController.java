package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//
@RestController
@RequestMapping("/user")
public class LoggedInUserController {

    ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    UserService userService;

    @RequestMapping(value = "/getUserInformation",  method = RequestMethod.POST)
    public String getUserInfo(@RequestParam("email") String email, @RequestParam("token") String token) throws JsonProcessingException {
        ClientUser userForEmail =userService.getUserForEmail(email);
        return objectMapper.writeValueAsString(userForEmail);
    }

    //Assuming email to be same and rest of the paramaters can be present or not
    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateUserProfile(@RequestParam("email") String email,
                                    @RequestParam("token") String token,
                                    @RequestParam(value = "firstName", required = false) String firstName,
                                    @RequestParam(value = "lastName", required = false) String lastName ,
                                    @RequestParam(value = "password", required = false) String password,
                                    @RequestParam(value = "phoneNum", required = false) String phoneNum ) throws JsonProcessingException {
        Map<String, String> resultMap = new HashMap<>();

        try {
            userService.updateProfile(email, firstName, lastName, phoneNum, password);
            resultMap.put("status", "success");
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("reason",e.getMessage());
        } finally {
            return objectMapper.writeValueAsString(resultMap);
        }
    }

    @RequestMapping(value = "/logout", method =  RequestMethod.POST)
    public String userLogout(@RequestParam("email") String email, @RequestParam("token") String token) throws Exception{
        Map<String, String> resultMap = new HashMap<>();

        try{

            userService.logout(email,token);
            resultMap.put("status","successfully logged out");
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            resultMap.put("status","failed");
            resultMap.put("reason",e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }


    }

}
