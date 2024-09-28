package com.project.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    @JsonIgnore
    byte[] storedSalt;
    @JsonIgnore
    byte[] password;
    @NotNull
    private List<AccountType> accountTypes;

    public User() {
    }

    public User(UUID id, String name, String email, byte[] storedSalt, byte[] password, List<AccountType> accountTypes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.storedSalt = storedSalt;
        this.password = password;
        this.accountTypes = accountTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public byte[] getStoredSalt() {
        return storedSalt;
    }

    public void setStoredSalt(byte[] storedSalt) {
        this.storedSalt = storedSalt;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public List<com.project.userservice.model.AccountType> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<com.project.userservice.model.AccountType> accountTypes) {
        this.accountTypes = accountTypes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", storedSalt=" + Arrays.toString(storedSalt) +
                ", password=" + Arrays.toString(password) +
                ", accountTypes=" + accountTypes +
                '}';
    }
}
