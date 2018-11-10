package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.service.UserService;
import com.windsor.foodapp.util.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();


    //todo : add logging
    @RequestMapping(value="/signIn", method = RequestMethod.POST)
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("phoneNumber") String phoneNumber) throws JsonProcessingException {

        Map<String, String> resultMap = new HashMap<>();


        ClientUser clientUser = new ClientUser(0,email, password, firstName, lastName, phoneNumber, CLIENT_STATUS_ENUM.ACTIVE);
        try {
            validateUserFields(clientUser);
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }
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

    @RequestMapping(value = "/loginFailed")
    public String loginFailed() throws JsonProcessingException {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("status", "failed");
        resultMap.put("reason","email/token not correct");
        return objectMapper.writeValueAsString(resultMap);
    }

    private void validateUserFields(ClientUser clientUser) throws Exception {
        String password_error = "The password is too short";
        String email_error = "Please enter a valid email";
        String name_error = "Please enter a valid name (upto 15 characters)";
        String phone_error = "Please enter a valid phone number";

        if (clientUser.getPassword().length() < 8) {
            throw new Exception(password_error);
        }
        if( (isAlpha(clientUser.getFirstName()) == false) || clientUser.getFirstName().length() > 15 || clientUser.getFirstName().length() < 1) {
            throw new Exception(name_error);
        }
        if ((isAlpha(clientUser.getLastName()) == false) || clientUser.getLastName().length() > 15 || clientUser.getLastName().length() < 1) {
            throw new Exception(name_error);
        }
        if (isValidNumber(clientUser.getPhoneNumber()) == false || clientUser.getPhoneNumber().length() < 1) {
            throw new Exception(phone_error);
        } else  {
            clientUser.setPhoneNumber(clientUser.getPhoneNumber().replace("-",""));
        }

        ValidationUtils.validateEmail(clientUser.getEmail());


    }

    public boolean isAlpha(String name) {
        if (name.matches("[a-zA-Z]+"))
        return true;
            else
        return false;
    }

    public boolean isValidNumber(String number) {
        return ValidationUtils.validatePhone(number);
    }



}

