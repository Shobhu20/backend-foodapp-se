package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.model.CustomerOrder;
import com.windsor.foodapp.model.OrderDetail;
import com.windsor.foodapp.service.OrderService;
import com.windsor.foodapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//
@RestController
@RequestMapping("/user")
public class LoggedInUserController {

    ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    UserService userService;

    @Resource
    OrderService orderService;

    @RequestMapping(value = "/getUserInformation", method = RequestMethod.POST)
    public String getUserInfo(@RequestParam("email") String email, @RequestParam("token") String token) throws JsonProcessingException {
        ClientUser userForEmail = userService.getUserForEmail(email);
        return objectMapper.writeValueAsString(userForEmail);
    }

    //Assuming email to be same and rest of the paramaters can be present or not
    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateUserProfile(@RequestParam("email") String email,
                                    @RequestParam("token") String token,
                                    @RequestParam(value = "firstName", required = false) String firstName,
                                    @RequestParam(value = "lastName", required = false) String lastName,
                                    @RequestParam(value = "password", required = false) String password,
                                    @RequestParam(value = "phoneNum", required = false) String phoneNum) throws JsonProcessingException {
        Map<String, String> resultMap = new HashMap<>();

        try {
            userService.updateProfile(email, firstName, lastName, phoneNum, password);
            resultMap.put("status", "success");
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
        } finally {
            return objectMapper.writeValueAsString(resultMap);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String userLogout(@RequestParam("email") String email, @RequestParam("token") String token) throws Exception {
        Map<String, String> resultMap = new HashMap<>();

        try {

            userService.logout(email, token);
            resultMap.put("status", "successfully logged out");
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }
    }

    @RequestMapping(value="/placeOrder", method = RequestMethod.POST)
    public String placeOrder(@RequestParam("email") String email, @RequestParam("token") String token,
                             @RequestBody Map<Integer, Integer> foodIdToQuantityMapJson) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();

        //0. get user name and reqd info for creating user
        // 1. get info of items from food item ...store it in a list of order items....also evaluate order object fields like cost
        //objectMapper.readValue(foodIdToQuantityMapJson, new TypeReference<Map<String, String>>(){});
        try {
            CustomerOrder customerOrder = orderService.placeOrder(email, foodIdToQuantityMapJson);
            resultMap.put("customer order", customerOrder);
            resultMap.put("status", "success");
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }
        //2. save order items
        //3. save order
        //4. return success
    }

    @RequestMapping(value="/getOrdersForUser", method = RequestMethod.POST)
    public String getOdersForUser(@RequestParam("email") String email, @RequestParam("token") String token) throws JsonProcessingException {
        Map<String, Object> resultMap = new HashMap<>();

        List<OrderDetail> customerOrders = orderService.getOrdersForCustomer(email);

        return objectMapper.writeValueAsString(resultMap);

    }

}
