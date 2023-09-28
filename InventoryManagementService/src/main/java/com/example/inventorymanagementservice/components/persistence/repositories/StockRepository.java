package com.example.inventorymanagementservice.components.persistence.repositories;

import com.example.inventorymanagementservice.components.persistence.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;



@Repository
public interface StockRepository extends JpaRepository<Stock, String > {

    Stock findStockByItemName(String name);

    Stock findStockByItemCode(String itemCode);

    Stock findStockByItemPrice(BigDecimal price);

    List<Stock> findStocksByItemPrice(BigDecimal price);

    void deleteStockByItemName(String itemName);

}
