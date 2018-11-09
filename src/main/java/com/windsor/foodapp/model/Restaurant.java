package com.windsor.foodapp.model;

public class Restaurant {

    private String name;
    private int id;
    private int fc_id;
    private String iconUrl;

    public Restaurant(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Restaurant(String name) {
        name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getFc_id() {
        return fc_id;
    }

    public void setName(String name) {
        name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFc_id(int fc_id) {
        this.fc_id = fc_id;
    }
}
