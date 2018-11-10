package com.windsor.foodapp.model;

import javax.annotation.Resource;

public class FoodItem {



    private int id;
    private String name;
    private double cost;
    private String category;
    private int restaurantId;
    private int foodCourtId;
    private int timeToPrepareInMinutes;
    private String restaurantName;
    private String foodCourtName;

    public FoodItem(int id, String name, double cost, String category, int restaurantId, int foodCourtId, int timeToPrepareInMinutes, String restaurantName, String foodCourtName) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.restaurantId = restaurantId;
        this.foodCourtId = foodCourtId;
        this.timeToPrepareInMinutes = timeToPrepareInMinutes;
        this.restaurantName = restaurantName;
        this.foodCourtName = foodCourtName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getFoodCourtId() {
        return foodCourtId;
    }

    public void setFoodCourtId(int foodCourtId) {
        this.foodCourtId = foodCourtId;
    }

    public int getTimeToPrepareInMinutes() {
        return timeToPrepareInMinutes;
    }

    public void setTimeToPrepareInMinutes(int timeToPrepareInMinutes) {
        this.timeToPrepareInMinutes = timeToPrepareInMinutes;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getFoodCourtName() {
        return foodCourtName;
    }

    public void setFoodCourtName(String foodCourtName) {
        this.foodCourtName = foodCourtName;
    }
}





























































































