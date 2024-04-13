package com.project.watersuppliersservice.repository;

import com.project.watersuppliersservice.model.WaterSuppliers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WaterSuppliersRepository extends CrudRepository<WaterSuppliers,UUID> {
    boolean existsWaterSuppliersByEmail(String email);
    boolean existsWaterSuppliersByUserId(UUID userId);
    WaterSuppliers findWaterSuppliersByUserId(UUID userId);
    UUID deleteWaterSuppliersByEmail(String email);
}
