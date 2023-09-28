package com.example.inventorymanagementservice.components.presentation.request_bodies.batch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispatchBatchRequest {

    private long batchId;
    private String carrier;

    private String destination;
}
