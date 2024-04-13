package com.project.locationservice.controller;

import com.project.locationservice.model.Location;
import com.project.locationservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/location")
public class  LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> getLocation(@RequestParam("lon") double longitude, @RequestParam("lat") double latitude){
        Location location = this.locationService.getLocations(longitude,latitude);
        return new ResponseEntity<Location>(location, HttpStatus.CREATED);
    }

    @PostMapping("/{locationId}/{userId}")
    public ResponseEntity<Location> setLocation(@PathVariable("locationId") String locationId,@PathVariable("userId") UUID userId ){
        Location location = this.locationService.setLocation(locationId,userId);
        return new ResponseEntity<Location>(location, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserLocation(@PathVariable("userId") UUID userId){
        return new ResponseEntity<>(locationService.getLocationByUserId(userId),HttpStatus.OK);
    }
}
