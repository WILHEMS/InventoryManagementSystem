package com.example.inventorymanagementservice.components.presentation.request_bodies.order;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CompleteOrderRequest {

    private long orderId;
    private String signer;
    private String location;
}
