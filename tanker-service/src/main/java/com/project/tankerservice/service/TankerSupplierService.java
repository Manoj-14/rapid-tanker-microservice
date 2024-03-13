package com.project.tankerservice.service;

import com.project.tankerservice.model.TankerSupplier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

public interface TankerSupplierService {
    @Transactional
    TankerSupplier register(TankerSupplier tankerSupplier, boolean isWaterSuppliers) throws DuplicateKeyException;
}
