package com.project.watersuppliersservice.repository;

import com.project.watersuppliersservice.model.WaterSuppliers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterSuppliersRepository extends CrudRepository<WaterSuppliers,Integer> {
    boolean existsWaterSuppliersByEmail(String email);
}
