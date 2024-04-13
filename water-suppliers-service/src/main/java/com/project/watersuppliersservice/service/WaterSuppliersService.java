package com.project.watersuppliersservice.service;

import com.project.watersuppliersservice.exception.AccountSetupException;
import com.project.watersuppliersservice.exception.SupplierNotFoundException;
import com.project.watersuppliersservice.model.WaterSuppliers;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface WaterSuppliersService {

    @Transactional
    WaterSuppliers register(WaterSuppliers suppliers) throws DuplicateKeyException;
    @Transactional
    WaterSuppliers update(WaterSuppliers suppliers, UUID userId) throws DuplicateKeyException;
    WaterSuppliers find(String Email) throws SupplierNotFoundException;
    @Transactional
    void delete(String Email) throws SupplierNotFoundException;

    @Transactional
    UUID createInActiveAccount(UUID userId) throws DuplicateKeyException ;
    WaterSuppliers findByUserId(UUID userId) throws SupplierNotFoundException, AccountSetupException;
}
