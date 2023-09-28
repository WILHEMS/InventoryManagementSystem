package com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.stock;

import com.example.inventorymanagementservice.components.persistence.entities.Stock;

/**
 * an interface to present stock fetch response.
 * @author kamar baraka.*/

public class StockFetchResponse {

    private String message;
    private Stock stock;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
