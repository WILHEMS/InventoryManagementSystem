package com.example.inventorymanagementservice.components.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * the batch of orders entity.
 * @author kamar baraka.*/

@Entity
@Getter
@Setter
@Table
public class BatchOfOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchId;

    @OneToMany(targetEntity = ItemOrder.class)
    private final List<ItemOrder> orders = new ArrayList<>();

    private String destination;

    @ManyToOne(targetEntity = User.class)
    private User carrier;

    private String location;

    private String status;

    private final LocalDateTime releaseDate = LocalDateTime.now();
}
