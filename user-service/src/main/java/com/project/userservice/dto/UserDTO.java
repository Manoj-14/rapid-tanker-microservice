package com.project.userservice.dto;

import com.project.userservice.model.AccountType;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String email;
    private String password;
    private AccountType accountType;

    public UserDTO() {
    }

    public UserDTO(UUID id, String email, String password, AccountType accountType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


}
