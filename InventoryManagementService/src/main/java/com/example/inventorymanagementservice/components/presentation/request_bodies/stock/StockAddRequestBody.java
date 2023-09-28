package com.example.inventorymanagementservice.components.presentation.request_bodies.stock;

import com.example.inventorymanagementservice.components.persistence.entities.ItemLocation;


public class StockAddRequestBody {

    private String name;
    private String code;
    private double price;
    private int count;

    private ItemLocation location;

    public ItemLocation getLocation() {
        return location;
    }

    public void setLocation(ItemLocation location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
