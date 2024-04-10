package com.project.userservice.dto;

import com.project.userservice.model.AccountType;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class AuthResponse implements Serializable {
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
