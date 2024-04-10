package com.project.locationservice.repository;

import com.project.locationservice.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends MongoRepository<Location, String> {
    List<Location> findAllLocationByUserId(String userId);
}
