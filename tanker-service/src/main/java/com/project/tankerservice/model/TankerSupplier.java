package com.project.tankerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class TankerSupplier {

    @Id
    private String id;
    private String email;
    private String name;
    private boolean waterSuppliers;
    @DBRef
    private List<Tanker> tankers = new ArrayList<>();

    public TankerSupplier() {
    }

    public TankerSupplier(String id, String email, String name, boolean waterSuppliers, List<Tanker> tankers) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.waterSuppliers = waterSuppliers;
        this.tankers = tankers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isWaterSuppliers() {
        return waterSuppliers;
    }

    public void setWaterSuppliers(boolean waterSuppliers) {
        this.waterSuppliers = waterSuppliers;
    }

    public List<Tanker> getTankers() {
        return tankers;
    }

    public void setTankers(List<Tanker> tankers) {
        this.tankers = tankers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TankerSupplier{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", waterSuppliers=" + waterSuppliers +
                ", tankers=" + tankers +
                '}';
    }
}
