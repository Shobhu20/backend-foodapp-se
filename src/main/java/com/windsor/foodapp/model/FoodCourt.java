package com.windsor.foodapp.model;

public class FoodCourt {

    private String foodCourtName;
    private String address;
    private String city;
    private String open_time;
    private String close_time;

    public FoodCourt(String foodCourtName, String address, String city, String open_time, String close_time) {
        this.foodCourtName = foodCourtName;
        this.address = address;
        this.city = city;
        this.open_time = open_time;
        this.close_time = close_time;
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

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }
}
