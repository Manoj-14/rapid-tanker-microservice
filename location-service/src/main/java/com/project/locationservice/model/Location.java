package com.project.locationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.UUID;

@Document
public class Location {
    @Id
    private String id;
    private String formatted;
    private String lat;
    private String lon;

    @DocumentReference
    private Address address;
    @JsonIgnore
    private String userId;

    public Location() {
    }

    public Location(String displayName, String latitude, String lon, Address address) {
        this.formatted = displayName;
        this.lat = latitude;
        this.lon = lon;
        this.address = address;
    }

    public Location(String id, String formatted, String lat, String lon, Address address, String userId) {
        this.id = id;
        this.formatted = formatted;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }


    public void setLat(String lat) {
        this.lat = lat;
    }


    public void setLon(String lon) {
        this.lon = lon;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", display_name='" + formatted + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", address=" + address +
                ", userId='" + userId + '\'' +
                '}';
    }
}
