package com.example.inventorymanagementservice.components.persistence.repositories;

import com.example.inventorymanagementservice.components.persistence.entities.CompletedOrders;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
public interface CompletedOrdersRepository extends CrudRepository<CompletedOrders, Long> {
}
