package com.project.locationservice.service;

import com.project.locationservice.exception.LocationNotFoundException;
import com.project.locationservice.model.Location;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    @Transactional
    Location setLocation(String locationId,UUID userId);
    Location getLocations(double longitude, double latitude) throws LocationNotFoundException;
    List<Location> getLocationByUserId(UUID userId);
}
