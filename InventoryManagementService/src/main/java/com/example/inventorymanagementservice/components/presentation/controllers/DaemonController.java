package com.example.inventorymanagementservice.components.presentation.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/daemon", consumes = {"application/json", "text/plain"}, produces = {"application/json"})
@CrossOrigin
public class DaemonController {
}
