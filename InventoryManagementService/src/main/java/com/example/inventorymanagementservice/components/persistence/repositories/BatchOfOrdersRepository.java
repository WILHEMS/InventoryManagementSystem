package com.example.inventorymanagementservice.components.persistence.repositories;

import com.example.inventorymanagementservice.components.persistence.entities.BatchOfOrders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@Repository
public interface BatchOfOrdersRepository extends CrudRepository<BatchOfOrders, Long> {

    BatchOfOrders findBatchOfOrdersByBatchId(long batchId);
}
