package com.example.inventorymanagementservice.components.persistence.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * an item entity.
 * @author kamar baraka.*/

@Entity
public class Stock {

    @Id
    @Column(unique = true)
    private String itemName;

    private String itemCode;

    private int itemCount;

    @OneToOne(cascade = {CascadeType.ALL})
    private ItemLocation location;

    private BigDecimal itemPrice;

    public Stock(String itemName, String itemCode, int itemCount, ItemLocation location, BigDecimal itemPrice) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemCount = itemCount;
        this.location = location;
        this.itemPrice = itemPrice;
    }

    public Stock() {
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public ItemLocation getLocation() {
        return location;
    }

    public void setLocation(ItemLocation location) {
        this.location = location;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
}
