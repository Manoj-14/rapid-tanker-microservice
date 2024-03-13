package com.project.tankerservice.controller;

import com.project.tankerservice.model.TankerSupplier;
import com.project.tankerservice.service.TankerSupplierService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tanker-supplier")
public class TankerSupplierController {

    private final TankerSupplierService tankerSupplierService;

    public TankerSupplierController(TankerSupplierService tankerSupplierService){
        this.tankerSupplierService = tankerSupplierService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody TankerSupplier tankerSupplier){
        TankerSupplier savedSupplier = tankerSupplierService.register(tankerSupplier,false);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }
}
