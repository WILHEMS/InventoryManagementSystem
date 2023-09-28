package com.example.inventorymanagementservice.components.persistence.repositories;

import com.example.inventorymanagementservice.components.persistence.entities.ReceivedOrders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@Repository
public interface ReceivedOrdersRepository extends CrudRepository<ReceivedOrders, Long> {

    void deleteReceivedOrdersByOrderId(long orderId);
}
