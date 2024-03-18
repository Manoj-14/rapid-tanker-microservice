package com.project.userservice.dto;

import com.project.userservice.model.AccountType;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class AuthResponse implements Serializable {
    private UUID id;
    private String name;
    private String email;
    private List<AccountType> AccountType;

    private String token;

    public AuthResponse() {
    }

    public AuthResponse(UUID id, String name, String email, List<com.project.userservice.model.AccountType> accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        AccountType = accountType;
    }

    public AuthResponse(UUID id, String name, String email, List<com.project.userservice.model.AccountType> accountType, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        AccountType = accountType;
        this.token = token;
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

    public List<com.project.userservice.model.AccountType> getAccountType() {
        return AccountType;
    }

    public void setAccountType(List<com.project.userservice.model.AccountType> accountType) {
        AccountType = accountType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
