package com.windsor.foodapp.model;

public class FoodCourt {

    private String foodCourtName;
    private String address;
    private String city;
    private String openTime;
    private String closeTime;

    public FoodCourt(String foodCourtName, String address, String city, String openTime, String closeTime) {
        this.foodCourtName = foodCourtName;
        this.address = address;
        this.city = city;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public String getFoodCourtName() {
        return foodCourtName;
    }

    public void setFoodCourtName(String foodCourtName) {
        this.foodCourtName = foodCourtName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}
