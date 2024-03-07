package com.project.watersuppliersservice.service;

import com.project.watersuppliersservice.model.WaterSuppliers;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

public interface WaterSuppliersService {

    @Transactional
    WaterSuppliers register(WaterSuppliers suppliers) throws DuplicateKeyException;
}
