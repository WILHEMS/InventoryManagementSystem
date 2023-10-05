package com.example.inventorymanagementservice.components.presentation.request_bodies.order;

import com.example.inventorymanagementservice.components.persistence.entities.ItemOrderDetails;

import java.util.List;


public class ItemOrderRequest {

    private String customerName;

    private List<ItemOrderDetails> items;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ItemOrderDetails> getItems() {
        return items;
    }

    public void setItems(List<ItemOrderDetails> items) {
        this.items = items;
    }
}
