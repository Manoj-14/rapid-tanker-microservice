package com.project.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;
    byte[] storedSalt;
    byte[] password;
    private List<AccountType> AccountType;

    public User() {
    }

    public User(UUID id, String email, byte[] storedSalt, byte[] password, List<com.project.userservice.model.AccountType> accountType) {
        this.id = id;
        this.email = email;
        this.storedSalt = storedSalt;
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

    public List<com.project.userservice.model.AccountType> getAccountType() {
        return AccountType;
    }

    public void setAccountType(List<com.project.userservice.model.AccountType> accountType) {
        AccountType = accountType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", storedSalt=" + Arrays.toString(storedSalt) +
                ", password=" + Arrays.toString(password) +
                ", AccountType=" + AccountType +
                '}';
    }
}
