package com.windsor.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.foodapp.enums.ORDER_STATUS_ENUM;
import com.windsor.foodapp.model.ClientUser;
import com.windsor.foodapp.model.CustomerOrder;
import com.windsor.foodapp.model.OrderDetail;
import com.windsor.foodapp.service.OrderService;
import com.windsor.foodapp.service.UserService;
import com.windsor.foodapp.util.ValidationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        Map<String, Object> resultMap = new HashMap<>();

        try {
            validateUserFields(email,firstName,lastName,phoneNum,password);
        } catch (Exception e){
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }

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

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String placeOrder(@RequestParam("email") String email, @RequestParam("token") String token,
                             @RequestParam("foodItemIds") String foodItemIdsCsv,
                             @RequestParam("quantity") String orderQuantityCsv) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String[] orderIds = foodItemIdsCsv.split(",");
            String[] orderQuantity = orderQuantityCsv.split(",");
            if (orderIds.length != orderQuantity.length)
                throw new Exception("please provide same number of items and quantity");
            Map<Integer, Integer> foodIdToQuantityMap = new HashMap<>();
            for (int i = 0; i < orderIds.length; i++) {
                try {
                    foodIdToQuantityMap.put(Integer.parseInt(orderIds[i]), Integer.parseInt(orderQuantity[i]));
                } catch (Exception e) {
                    throw new Exception("please provide number in id and quantity");
                }
            }
            CustomerOrder customerOrder = orderService.placeOrder(email, foodIdToQuantityMap);
            resultMap.put("customer order", customerOrder);
            resultMap.put("status", "success");
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("reason", e.getMessage());
            return objectMapper.writeValueAsString(resultMap);
        }
    }

    @RequestMapping(value = "/getOrdersForUser", method = RequestMethod.POST)
    public String getOdersForUser(@RequestParam("email") String email, @RequestParam("token") String token, @RequestParam(value = "restaurantId", required = false) Integer restaurantId) throws JsonProcessingException {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            LinkedHashMap<String, List<OrderDetail>> customerOrders = orderService.getOrdersForParameters(email, restaurantId);
            resultMap.put("OrderList", customerOrders);
            resultMap.put("status", "success");
        } catch (Exception e) {
            resultMap.put("status", "failed");
            resultMap.put("errorMessage", e.getMessage());
        } finally {
            return objectMapper.writeValueAsString(resultMap);
        }
    }

    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
    public String updateOrderStatus(@RequestParam("email") String email, @RequestParam("token") String token, @RequestParam("orderId") Integer orderId, @RequestParam("orderStatus") String orderStatus) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (StringUtils.isEmpty(orderStatus))
                throw new Exception("order status cannot be empty");
            if (!orderStatus.equalsIgnoreCase("ACTIVE") && !orderStatus.equalsIgnoreCase("COMPLETED"))
                throw new Exception("order status can only be active or completed");
            Integer restaurantId = orderService.getRestaurantIdForUser(email);
            orderService.updateOrderStatus(restaurantId, orderId, ORDER_STATUS_ENUM.valueOf(orderStatus.toUpperCase()));
            resultMap.put("status", "success");

        } catch (Exception e) {
            resultMap.put("status", "failure");
            resultMap.put("message", e.getMessage());

        } finally {
            return objectMapper.writeValueAsString(resultMap);
        }
    }

    private void validateUserFields(String email,String firstName, String lastName,String phoneNum, String password) throws Exception {
        String password_error = "The password is too short";
        String email_error = "Please enter a valid email";
        String name_error = "Please enter a valid name (upto 15 characters)";
        String phone_error = "Please enter a valid phone number";

        if(!StringUtils.isEmpty(password))
        if (password.length() < 8 ) {
            throw new Exception(password_error);
        }
        if(!StringUtils.isEmpty(firstName))
        if( (isAlpha(firstName) == false) || firstName.length() > 15 || firstName.length() < 1) {
            throw new Exception(name_error);
        }
        if(!StringUtils.isEmpty(lastName))
        if ((isAlpha(lastName) == false) || lastName.length() > 15 || lastName.length() < 1) {
            throw new Exception(name_error);
        }
        if(!StringUtils.isEmpty(phoneNum))
        if (isValidNumber(phoneNum) == false || phoneNum.length() < 1) {
            throw new Exception(phone_error);
        } else  {
            phoneNum = (phoneNum.replace("-",""));
        }

        ValidationUtils.validateEmail(email);


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
