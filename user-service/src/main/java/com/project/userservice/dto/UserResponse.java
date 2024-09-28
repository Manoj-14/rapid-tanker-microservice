package com.project.userservice.dto;

import com.project.userservice.model.AccountType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserResponse {
    UUID id;
    String name;
    String email;
    List<Object> address;
    List<AccountType> accountTypes;

    public UserResponse() {
    }

    public UserResponse(String name, String email, List<Object> address, List<AccountType> accountTypes) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.accountTypes = accountTypes;
    }

    public UserResponse(UUID id, String name, String email, List<Object> address, List<AccountType> accountTypes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.accountTypes = accountTypes;
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

    public List<Object> getAddress() {
        return address;
    }

    public void setAddress(List<Object> address) {
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<AccountType> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<AccountType> accountTypes) {
        this.accountTypes = accountTypes;
    }
}
