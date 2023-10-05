package com.example.inventorymanagementservice.components.business.services;

import com.example.inventorymanagementservice.components.persistence.entities.BatchOfOrders;
import com.example.inventorymanagementservice.components.persistence.entities.User;
import com.example.inventorymanagementservice.components.persistence.repositories.BatchOfOrdersRepository;
import com.example.inventorymanagementservice.components.persistence.repositories.UserRepository;
import com.example.inventorymanagementservice.components.presentation.request_bodies.batch.DispatchBatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchManagementService {

    private final UserRepository userRepository;
    private final BatchOfOrdersRepository batchOfOrdersRepository;

    @Autowired
    public BatchManagementService(UserRepository userRepository, BatchOfOrdersRepository batchOfOrdersRepository) {
        this.userRepository = userRepository;
        this.batchOfOrdersRepository = batchOfOrdersRepository;
    }

    public boolean dispatchBatch(DispatchBatchRequest request){

        /*get the carrier*/
//        User carrier = this.userRepository.findUserByUsername(request.getCarrier());
        User carrier = this.userRepository.findUserByUsername(request.getCarrier());

        if (carrier == null || !carrier.getRole().equals("CARRIER"))
            return false;

        /*get the batch*/
        BatchOfOrders batch = this.batchOfOrdersRepository.findBatchOfOrdersByBatchId(request.getBatchId());

        /*update the batch*/
        batch.setStatus("transit");
        batch.setCarrier(carrier);
        batch.setDestination(request.getDestination());
        this.batchOfOrdersRepository.save(batch);

        return true;
    }
}
