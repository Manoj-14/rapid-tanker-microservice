package com.project.watersuppliersservice.controller;

import com.project.watersuppliersservice.model.WaterSuppliers;
import com.project.watersuppliersservice.service.WaterSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @PostMapping("/create")
    public ResponseEntity<?> createInActiveAccount(@RequestParam("userId") UUID userId){
        String id = suppliersService.createInActiveAccount(userId).toString();
        Map<String,String> response = new HashMap<>();
        response.put("id",id);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> update(@RequestBody WaterSuppliers suppliers,@PathVariable("userId") UUID userId){
        WaterSuppliers savedSupplier = suppliersService.update(suppliers,userId);
        return new ResponseEntity<>(savedSupplier,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WaterSuppliers> getSupplierAccount(@PathVariable("userId") UUID userId){
        WaterSuppliers suppliers = suppliersService.findByUserId(userId);
        return new ResponseEntity<>(suppliers,HttpStatus.OK);
    }
}
