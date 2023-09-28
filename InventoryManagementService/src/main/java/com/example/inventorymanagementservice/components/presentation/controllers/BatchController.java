package com.example.inventorymanagementservice.components.presentation.controllers;

import com.example.inventorymanagementservice.components.business.services.BatchManagementService;
import com.example.inventorymanagementservice.components.presentation.request_bodies.batch.DispatchBatchRequest;
import com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.batch.BatchDispatchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BatchController {

    private final BatchManagementService batchManagementService;

    @Autowired
    public BatchController(BatchManagementService batchManagementService) {
        this.batchManagementService = batchManagementService;
    }

    @PostMapping(value = {"batch/dispatch"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin(methods = {RequestMethod.POST})
    public ResponseEntity<BatchDispatchResponse> dispatch(@RequestBody DispatchBatchRequest request){

        /*construct a response*/
        BatchDispatchResponse response = new BatchDispatchResponse();

        /*process the response*/
        this.batchManagementService.dispatchBatch(request);

        /*set the response*/
        response.setMessage("batch dispatched successfully");

        /*return the response*/
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
