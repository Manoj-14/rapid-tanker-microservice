package com.project.locationservice.service;

import com.project.locationservice.exception.LocationNotFoundException;
import com.project.locationservice.feign.Geoapify;
import com.project.locationservice.feign.OpenRouteService;
import com.project.locationservice.feign.OpenStreetMap;
import com.project.locationservice.model.Address;
import com.project.locationservice.model.Location;
import com.project.locationservice.repository.AddressRepository;
import com.project.locationservice.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final OpenRouteService openRouteService;
    private final OpenStreetMap openStreetMap;
    private  final Geoapify geoapify;
    private final LocationRepository locationRepository;
    private final AddressRepository addressRepository;
    @Value("${apiKey}")
    private String apiKey;

    @Autowired
    public LocationServiceImpl(OpenRouteService openRouteService, OpenStreetMap openStreetMap, LocationRepository locationRepository, AddressRepository addressRepository, Geoapify geoapify) {
        this.openRouteService = openRouteService;
        this.openStreetMap = openStreetMap;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
        this.geoapify=geoapify;
    }

    public Location getLocations(double longitude, double latitude) {
        Map<String,Object> data = this.geoapify.getLocations(apiKey,latitude,longitude);
        List<Object> features = (ArrayList<Object>) data.get("features");
        ModelMapper mapper = new ModelMapper();
        Map<String,Object> rawLocation = (Map<String, Object>) features.get(0);
        Location location = mapper.map(rawLocation.get("properties"),Location.class);
        Address address = mapper.map(rawLocation.get("properties"),Address.class);
        location.setAddress(address);
        return location;
//        Map<String,Object> data = this.openRouteService.getLocations(apiKey,longitude,latitude);
//        ArrayList<Map<String,Object>> features = (ArrayList<Map<String, Object>>) data.get("features");
//        for (Map<String,Object> feature:features) {
//            Map<String ,Object> someData = (Map<String, Object>) feature.get("properties");
//            System.out.println(someData);
//        }
    }

    @Override
    public List<Location> getLocationByUserId(UUID userId) {
        List<Location> savedLocations = locationRepository.findAllLocationByUserId(userId.toString());
        return savedLocations;
    }

    @Override
    public Location setLocation(UUID userId,double latitude,double longitude) {
        Location location = getLocations( longitude, latitude);
        Address savedAddress = addressRepository.save(location.getAddress());
        location.setUserId(userId.toString());
        return locationRepository.save(location);
    }
}
