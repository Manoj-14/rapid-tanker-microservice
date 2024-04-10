package com.project.locationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
    @JsonIgnore
    @Id
    private String id;
    private String road;
    private String village;
    private String city_district;
    private String state_district;
    private String city;
    private String state;
    private String country;
    private String postcode;

    public Address() {
    }

    public Address(String id, String road, String village, String city_district, String stateDistrict, String city, String state, String country, String postcode) {
        this.id = id;
        this.road = road;
        this.village = village;
        this.city_district = city_district;
        state_district = stateDistrict;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCity_district() {
        return city_district;
    }

    public void setCity_district(String city_district) {
        this.city_district = city_district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState_district() {
        return state_district;
    }

    public void setState_district(String state_district) {
        this.state_district = state_district;
    }

    @Override
    public String toString() {
        return "Address{" +
                "road='" + road + '\'' +
                ", village='" + village + '\'' +
                ", city_district='" + city_district + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
