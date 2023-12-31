package com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.order;

/**
 * an interface to present order add response.
 * @author kamar baraka.*/

public class OrderAddResponse {

    private String message;

    private OrderDetails orderDetails;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
