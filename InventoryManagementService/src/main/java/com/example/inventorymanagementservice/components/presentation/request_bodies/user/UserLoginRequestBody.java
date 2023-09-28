package com.example.inventorymanagementservice.components.presentation.request_bodies.user;

import org.springframework.web.bind.annotation.RequestBody;

public class UserLoginRequestBody {

    private String username;

    private String password;

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
}
