package com.windsor.foodapp.model;

import com.windsor.foodapp.enums.ORDER_STATUS_ENUM;

import java.util.Date;

public class CustomerOrder {
    private int id;
    private int userId;
    private String userName;
    private double totalCost;
    private Date orderTime;
    private int foodCourtId;
    private String foodCourtName;
    private ORDER_STATUS_ENUM order_status;
    private long prepTime;

    public CustomerOrder(int id, int userId, String userName, double totalCost, Date orderTime, int foodCourtId, String foodCourtName, ORDER_STATUS_ENUM order_status, long prepTime) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.totalCost = totalCost;
        this.orderTime = orderTime;
        this.foodCourtId = foodCourtId;
        this.foodCourtName = foodCourtName;
        this.order_status = order_status;
        this.prepTime = prepTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getFoodCourtId() {
        return foodCourtId;
    }

    public void setFoodCourtId(int foodCourtId) {
        this.foodCourtId = foodCourtId;
    }

    public String getFoodCourtName() {
        return foodCourtName;
    }

    public void setFoodCourtName(String foodCourtName) {
        this.foodCourtName = foodCourtName;
    }

    public ORDER_STATUS_ENUM getOrder_status() {
        return order_status;
    }

    public void setOrder_status(ORDER_STATUS_ENUM order_status) {
        this.order_status = order_status;
    }

    public long getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(long prepTime) {
        this.prepTime = prepTime;
    }
}
