package com.example.inventorymanagementservice.components.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * the completed order entity.
 * @author kamar baraka.*/

@Entity
@Getter
@Setter
public class CompletedOrders {

    @Id
    private long orderId;

    @ManyToOne(targetEntity = User.class)
    private User signer;

    private String location;

    private final LocalDateTime timeCompleted = LocalDateTime.now();
}
