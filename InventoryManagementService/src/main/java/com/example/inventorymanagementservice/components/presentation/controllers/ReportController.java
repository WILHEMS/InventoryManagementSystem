package com.example.inventorymanagementservice.components.presentation.controllers;

import com.example.inventorymanagementservice.components.presentation.response_bodies.implementation.report.CustomerReportResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"/report"}, consumes = {"application/json"}, produces = {"application/json"})
@CrossOrigin
public class ReportController {

    public ResponseEntity<CustomerReportResponse> customerReport(){
        return null;
    }
}
