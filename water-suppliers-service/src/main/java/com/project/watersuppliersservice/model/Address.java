package com.project.watersuppliersservice.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String town;
    private String street;

    private String city;
    private String state;

    private String country;

    private String zip;


    public Address() {
    }

    public Address(String town, String street, String city, String state, String country, String zip) {
        this.town = town;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
