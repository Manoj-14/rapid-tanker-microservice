package com.project.tankerservice.repository;

import com.project.tankerservice.model.TankerSupplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TankerSupplierRepository extends MongoRepository<TankerSupplier, String> {
    boolean existsTankerSupplierByEmail(String email);
}
