package com.project.watersuppliersservice.controller;

import com.project.watersuppliersservice.model.WaterSuppliers;
import com.project.watersuppliersservice.service.WaterSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
public class WaterSupplierController {

    private  final WaterSuppliersService suppliersService;
    @Autowired
    public WaterSupplierController(WaterSuppliersService suppliersService){
        this.suppliersService = suppliersService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody WaterSuppliers suppliers){
        WaterSuppliers waterSuppliers = this.suppliersService.register(suppliers);
        return new ResponseEntity<>(waterSuppliers, HttpStatus.CREATED);
    }
}
