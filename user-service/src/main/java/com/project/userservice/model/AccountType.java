package com.project.userservice.model;

public enum AccountType {
    USER("user"),WATER_SUPPLIER("water_supplier"),TANKER_SUPPLIER("tanker_supplier"),TANKER("tanker");
    private String role;
    AccountType(String role) {
        this.role = role;
    }

}
