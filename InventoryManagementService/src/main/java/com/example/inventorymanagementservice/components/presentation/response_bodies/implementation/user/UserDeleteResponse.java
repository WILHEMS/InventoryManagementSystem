package com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.user;

import com.example.inventorymanagementservice.components.presentation.response_bodies.interfaces.UserResponse;

/**
 * an interface to present user delete response.
 * @author kamar baraka.*/

public class UserDeleteResponse implements UserResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
