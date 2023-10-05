package com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.user;

import com.example.inventorymanagementservice.components.persistence.entities.User;
import com.example.inventorymanagementservice.components.presentation.response_bodies.interfaces.UserResponse;

/**
 * an interface to represent user update response.
 * @author kamar baraka.*/

public class UserUpdateResponse implements UserResponse {

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
