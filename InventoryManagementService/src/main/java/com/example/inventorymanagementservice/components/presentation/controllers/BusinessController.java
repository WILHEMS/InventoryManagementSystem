package com.example.inventorymanagementservice.components.presentation.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/business", consumes = {"application/json", "text/plain"}, produces = {"applicatio/json"})
@CrossOrigin
public class BusinessController {
}
