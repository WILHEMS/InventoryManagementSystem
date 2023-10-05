package com.example.inventorymanagementservice.components.persistence.repositories;

import com.example.inventorymanagementservice.components.persistence.entities.DispatchedOrders;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;



@Repository
public interface DispatchedOrdersRepository extends CrudRepository<DispatchedOrders, Long> {

    void deleteDispatchedOrdersByOrderId(long orderId);
}
