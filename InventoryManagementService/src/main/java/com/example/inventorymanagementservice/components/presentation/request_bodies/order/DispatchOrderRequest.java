package com.example.inventorymanagementservice.components.presentation.request_bodies.order;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DispatchOrderRequest {

    private long[] orderIds;
    private String usernameOfDispatcher;

    private String dispatchLocation;
}
