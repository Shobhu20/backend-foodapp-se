package com.windsor.foodapp.model;

import com.windsor.foodapp.enums.CLIENT_STATUS_ENUM;

public class ClientUser {

    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private CLIENT_STATUS_ENUM status;

    public ClientUser(int id, String email, String password, String firstName, String lastName, String phoneNumber, CLIENT_STATUS_ENUM status) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CLIENT_STATUS_ENUM getStatus() {
        return status;
    }

    public void setStatus(CLIENT_STATUS_ENUM status) {
        this.status = status;
    }
}
