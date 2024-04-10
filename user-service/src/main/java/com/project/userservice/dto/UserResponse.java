package com.project.userservice.dto;

import com.project.userservice.model.AccountType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserResponse {
    UUID id;
    String email;
    List<Object> address;
    List<AccountType> accountTypes;

    public UserResponse() {
    }

    public UserResponse(String email, List<Object> address, List<AccountType> accountTypes) {
        this.email = email;
        this.address = address;
        this.accountTypes = accountTypes;
    }

    public UserResponse(UUID id, String email, List<Object> address, List<AccountType> accountTypes) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.accountTypes = accountTypes;
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
