package com.project.watersuppliersservice.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class WaterSuppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    private String name;

    private String email;

    private String owner;

    private boolean hasTanker;
    private boolean accountSetUpCompleted;

    public WaterSuppliers() {
    }

    public WaterSuppliers(UUID id, UUID userId, String name, String email, String owner, boolean hasTanker, boolean accountSetUpCompleted) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.owner = owner;
        this.hasTanker = hasTanker;
        this.accountSetUpCompleted = accountSetUpCompleted;
    }

    public boolean isHasTanker() {
        return hasTanker;
    }

    public void setHasTanker(boolean hasTanker) {
        this.hasTanker = hasTanker;
    }

    @Override
    public String toString() {
        return "WaterSuppliers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public boolean isNull(){
        return this.name == null || this.email == null ;
    }

    public boolean isAccountSetUpCompleted() {
        return accountSetUpCompleted;
    }

    public void setAccountSetUpCompleted(boolean accountSetUpCompleted) {
        this.accountSetUpCompleted = accountSetUpCompleted;
    }


    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
    public void copyClass(WaterSuppliers suppliers){
        this.email = suppliers.email;
        this.name = suppliers.name;
        this.hasTanker = suppliers.hasTanker;
        this.owner = suppliers.owner;
    }
}
