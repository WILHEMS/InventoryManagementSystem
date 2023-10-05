package com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.stock;

import com.example.inventorymanagementservice.components.persistence.entities.Stock;

import java.util.List;

/**
 * response body for all stocks.
 * @author kamar baraka.*/

public class Stocks {

    private String message;

    private List<Stock> stockList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
}
