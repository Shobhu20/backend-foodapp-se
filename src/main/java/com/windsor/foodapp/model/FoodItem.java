package com.windsor.foodapp.model;

import javax.annotation.Resource;

public class FoodItem {



    private int id;
    private String name;
    private double cost;
    private String category;
    private String timeToPrepareInMinutes;
    private int r_id;




    public FoodItem(int id, String name, double cost, String category, String timeToPrepareInMinutes, int r_id) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.timeToPrepareInMinutes = timeToPrepareInMinutes;
        this.r_id=r_id;

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

    public String getTimeToPrepareInMinutes() {
        return timeToPrepareInMinutes;
    }

    public void setTimeToPrepareInMinutes(String timeToPrepareInMinutes) {
        this.timeToPrepareInMinutes = timeToPrepareInMinutes;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }
}
