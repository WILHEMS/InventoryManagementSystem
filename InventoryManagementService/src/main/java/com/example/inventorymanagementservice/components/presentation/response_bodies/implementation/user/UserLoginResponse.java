package com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.user;

import com.example.inventorymanagementservice.components.presentation.response_bodies.interfaces.UserResponse;
import com.example.inventorymanagementservice.components.persistence.entities.User;

/**
 * an interface to represent user login response.
 * @author kamar baraka.*/

public class UserLoginResponse implements UserResponse {

    private String message;

    private User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
