package com.project.userservice.dto;

import com.project.userservice.model.AccountType;

import java.util.List;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String email;
    private String password;
    private List<AccountType> AccountType;

    public UserDTO() {
    }

    public UserDTO(UUID id, String email, String password, List<com.project.userservice.model.AccountType> accountType) {
        this.id = id;
        this.email = email;
        this.password = password;
        AccountType = accountType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<com.project.userservice.model.AccountType> getAccountType() {
        return AccountType;
    }

    public void setAccountType(List<com.project.userservice.model.AccountType> accountType) {
        AccountType = accountType;
    }


}
