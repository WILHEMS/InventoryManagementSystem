package com.example.inventorymanagementservice.components.presentation.request_bodies.user;

import com.example.inventorymanagementservice.components.persistence.entities.User;


public class UserUpdateRequestBody {

    private String username;

    private String password;

    private User user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
