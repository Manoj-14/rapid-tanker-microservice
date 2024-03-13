package com.project.tankerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.UUID;

@Document
public class Tanker {
    @Id
    private UUID id;
    private String VehicalNumber;
    private Type type;
    @DocumentReference
    private Driver Driver;

    public Tanker() {
    }

    public Tanker(UUID id, String vehicalNumber, Type type, com.project.tankerservice.model.Driver driver) {
        this.id = id;
        VehicalNumber = vehicalNumber;
        this.type = type;
        Driver = driver;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVehicalNumber() {
        return VehicalNumber;
    }

    public void setVehicalNumber(String vehicalNumber) {
        VehicalNumber = vehicalNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public com.project.tankerservice.model.Driver getDriver() {
        return Driver;
    }

    public void setDriver(com.project.tankerservice.model.Driver driver) {
        Driver = driver;
    }

    @Override
    public String toString() {
        return "Tanker{" +
                "id=" + id +
                ", VehicalNumber='" + VehicalNumber + '\'' +
                ", type=" + type +
                ", Driver=" + Driver +
                '}';
    }
}
