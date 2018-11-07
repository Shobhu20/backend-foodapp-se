package com.windsor.foodapp.model;

public class FoodItem {

    private int id;
    private String name;
    private double cost;
    private String category;

    public FoodItem(int id, String name, double cost, String category) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
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
}
