package com.project.watersuppliersservice.service;

import com.project.watersuppliersservice.exception.SupplierNotFoundException;
import com.project.watersuppliersservice.model.WaterSuppliers;
import jakarta.validation.constraints.Email;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

public interface WaterSuppliersService {

    @Transactional
    WaterSuppliers register(WaterSuppliers suppliers) throws DuplicateKeyException;
    @Transactional
    WaterSuppliers update(WaterSuppliers suppliers, String email) throws DuplicateKeyException;

    WaterSuppliers find(String Email) throws SupplierNotFoundException;

    void delete(String Email) throws SupplierNotFoundException;
}
