package com.example.inventorymanagementservice.components.presentation.request_bodies.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveOrderRequest {

    private long batchId;
    private String recipient;
    private String location;
}
