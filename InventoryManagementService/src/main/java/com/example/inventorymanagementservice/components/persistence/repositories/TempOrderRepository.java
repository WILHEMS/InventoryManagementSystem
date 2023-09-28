package com.example.inventorymanagementservice.components.persistence.repositories;

import com.example.inventorymanagementservice.components.persistence.entities.TempOrder;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
public interface TempOrderRepository extends CrudRepository<TempOrder, Long> {

    TempOrder findTempOrderByOrderId(long orderId);

    void deleteTempOrderByOrderId(long orderId);
}
