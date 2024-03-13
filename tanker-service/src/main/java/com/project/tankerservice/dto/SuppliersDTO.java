package com.project.tankerservice.dto;

import java.io.Serializable;

public class SuppliersDTO implements Serializable {

    private String id;
    private String email;

    public SuppliersDTO() {
    }

    public SuppliersDTO(String id, String email) {
        this.id = id;
        this.email = email;
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

    @Override
    public String toString() {
        return "SuppliersDTO{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
