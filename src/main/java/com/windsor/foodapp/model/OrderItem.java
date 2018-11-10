package com.windsor.foodapp.model;

public class OrderItem {
    private int id;
    private String name;
    private int orderId;
    private int restaurantId;
    private String restaurantName;
    private double itemCost;
    private int quantity;

    public OrderItem(int id, String name, int orderId, int restaurantId, String restaurantName, double itemCost, int quantity) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.itemCost = itemCost;
        this.quantity = quantity;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
