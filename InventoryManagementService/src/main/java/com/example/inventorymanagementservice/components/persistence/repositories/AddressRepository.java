package com.example.inventorymanagementservice.components.persistence.repositories;

import com.example.inventorymanagementservice.components.persistence.entities.Address;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
