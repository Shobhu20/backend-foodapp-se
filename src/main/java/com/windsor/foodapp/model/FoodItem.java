package com.windsor.foodapp.model;

public class FoodItem {

    private int id;
    private String name;
    private double cost;
    private String category;
    private int restaurantId;
    private int foodCourtid;
    private long timeToPrepareInMinutes;
    private String restuarantName;
    private String foodCourtName;

    public FoodItem(int id, String name, double cost, String category, int restaurantId, int foodCourtid, long timeToPrepareInMinutes, String restuarantName, String foodCourtName) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.restaurantId = restaurantId;
        this.foodCourtid = foodCourtid;
        this.timeToPrepareInMinutes = timeToPrepareInMinutes;
        this.restuarantName = restuarantName;
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

    public long getTimeToPrepareInMinutes() {
        return timeToPrepareInMinutes;
    }

    public void setTimeToPrepareInMinutes(long timeToPrepareInMinutes) {
        this.timeToPrepareInMinutes = timeToPrepareInMinutes;
    }

    public int getFoodCourtid() {
        return foodCourtid;
    }

    public void setFoodCourtid(int foodCourtid) {
        this.foodCourtid = foodCourtid;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestuarantName() {
        return restuarantName;
    }

    public void setRestuarantName(String restuarantName) {
        this.restuarantName = restuarantName;
    }

    public String getFoodCourtName() {
        return foodCourtName;
    }

    public void setFoodCourtName(String foodCourtName) {
        this.foodCourtName = foodCourtName;
    }
}
