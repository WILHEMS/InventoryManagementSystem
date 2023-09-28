package com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.order;

import lombok.Getter;
import lombok.Setter;

/**
 * the order dispatch response.
 * @author kamar baraka.*/

@Getter
@Setter
public class OrderDispatchResponse {

    private String message;
    private long batchId;
}
