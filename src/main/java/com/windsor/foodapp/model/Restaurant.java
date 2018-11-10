package com.windsor.foodapp.model;

public class Restaurant {

    private int id;
    private String name;
    private String iconUrl;
    private int fc_id;

    public Restaurant(int id,String name, String iconUrl, int fc_id) {

        this.id=id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.fc_id=fc_id;

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getFc_id() {
        return fc_id;
    }

    public void setFc_id(int fc_id) {
        this.fc_id = fc_id;
    }
}
