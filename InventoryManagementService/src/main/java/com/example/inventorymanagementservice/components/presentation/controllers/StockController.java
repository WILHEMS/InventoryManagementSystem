package com.example.inventorymanagementservice.components.presentation.controllers;

import com.example.inventorymanagementservice.components.business.services.StockManagementService;
import com.example.inventorymanagementservice.components.business.services.StockReportService;
import com.example.inventorymanagementservice.components.presentation.request_bodies.stock.StockAddRequestBody;
import com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.stock.*;
import com.example.inventorymanagementservice.components.persistence.entities.Stock;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;


@RestController
public class StockController {

    private StockManagementService stockManagementService;
    private StockReportService stockReportService;

    /*inject the service*/
    @Autowired
    public StockController(StockManagementService stockManagementService, StockReportService stockReportService) {
        this.stockManagementService = stockManagementService;
        this.stockReportService = stockReportService;
    }

    @PostMapping(value = {"/stock/add"}, consumes = {"application/json"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.POST})
    public ResponseEntity<StockAddResponse> add(@RequestBody StockAddRequestBody requestBody){

        /*construct the stock add response*/
        StockAddResponse stockAddResponse = new StockAddResponse();

        if (!this.stockManagementService.addStock(requestBody)){

            stockAddResponse.setMessage("an error occurred! Failed to save stock");

            return new ResponseEntity<>(stockAddResponse, HttpStatus.OK);
        }

        stockAddResponse.setMessage("successfully added");

        return new ResponseEntity<>(stockAddResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = {"stock/update"}, consumes = {"application/json", "text/plain"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.PUT})
    public ResponseEntity<StockUpdateResponse> update(@RequestBody StockAddRequestBody requestBody){

        /*construct the response*/
        StockUpdateResponse stockUpdateResponse = new StockUpdateResponse();

        Stock stock = this.stockManagementService.updateStock(requestBody);

        if (stock == null){

            stockUpdateResponse.setMessage("no such stock to update");
            stockUpdateResponse.setStock(null);

            return new ResponseEntity<>(stockUpdateResponse, HttpStatus.NOT_FOUND);
        }

        stockUpdateResponse.setMessage("successfully updated.");
        stockUpdateResponse.setStock(stock);

        return new ResponseEntity<>(stockUpdateResponse, HttpStatus.OK);
    }

    @GetMapping(value = {"stock/fetch"}, consumes = {"application/json", "text/plain"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.GET})
    public ResponseEntity<StockFetchResponse> fetch(@RequestParam("item_name") String itemName){

        /*construct the response*/
        StockFetchResponse fetchResponse = new StockFetchResponse();

        Stock stock = this.stockManagementService.getStockByName(itemName);

        if (stock == null){
            fetchResponse.setMessage("item not found");
            fetchResponse.setStock(null);
            return new ResponseEntity<>(fetchResponse, HttpStatus.NOT_FOUND);
        }

        fetchResponse.setMessage("resource found");
        fetchResponse.setStock(stock);

        return new ResponseEntity<>(fetchResponse, HttpStatus.OK);

    }

    @DeleteMapping(value = {"stock/delete/{stock_name}"}, consumes = {"application/json", "text/plain"}, produces = {"application/json"})
    @CrossOrigin(methods = {RequestMethod.DELETE})
    public ResponseEntity<StockDeleteResponse> delete(@PathVariable("stock_name") String itemName){

        /*construct a response*/
        StockDeleteResponse deleteResponse = new StockDeleteResponse();

        if (!this.stockManagementService.deleteStock(itemName)){

            /*set the message*/
            deleteResponse.setMessage("unable to delete item");

            /*return the response*/
            return new ResponseEntity<>(deleteResponse, HttpStatus.CONFLICT);
        }

        /*set the message*/
        deleteResponse.setMessage("delete successful");

        /*return the response*/
        return new ResponseEntity<>(deleteResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = {"/stock"}, produces = {"application/json"})
    public ResponseEntity<Stocks> allStocks(){

        /*construct the response*/
        Stocks stockResponse = new Stocks();

        List<Stock> stockList = this.stockManagementService.getAllStock();

        stockResponse.setMessage("found %s stock".formatted(stockList.size()));
        stockResponse.setStockList(stockList);

        return new ResponseEntity<>(stockResponse, HttpStatus.OK);
    }
    /**
     * JasperReportGeneration.
     * The Request body must contain the properties;
     * format - the format of the report.
     * @param format-  the body of the request.*/
    @GetMapping("/stockReport/{format}")
    public String generatedReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return stockReportService.exportStockReport(format);
    }

}
