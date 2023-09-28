package com.example.inventorymanagementservice.components.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * the received order entity.
 * @author kamar baraka.*/

@Entity
@Getter
@Setter
public class ReceivedOrders {

    @Id
    private long orderId;

    @ManyToOne(targetEntity = User.class)
    private User recipient;

    private String receiveLocation;

    private final LocalDateTime receivedDate = LocalDateTime.now();
}
