package com.project.watersuppliersservice.model;

import jakarta.persistence.*;

@Entity
public class WaterSuppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String email;

    private String owner;

    @Embedded
    private Address Address;

    public WaterSuppliers() {
    }

    public WaterSuppliers(String name, String email, String owner, com.project.watersuppliersservice.model.Address address) {
        this.name = name;
        this.email = email;
        this.owner = owner;
        Address = address;
    }

    public WaterSuppliers(int id, String name, String email, String owner, com.project.watersuppliersservice.model.Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.owner = owner;
        Address = address;
    }

    @Override
    public String toString() {
        return "WaterSuppliers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", owner='" + owner + '\'' +
                ", Address=" + Address +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public com.project.watersuppliersservice.model.Address getAddress() {
        return Address;
    }

    public void setAddress(com.project.watersuppliersservice.model.Address address) {
        Address = address;
    }
}
