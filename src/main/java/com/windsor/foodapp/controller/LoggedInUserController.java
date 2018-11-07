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

}
