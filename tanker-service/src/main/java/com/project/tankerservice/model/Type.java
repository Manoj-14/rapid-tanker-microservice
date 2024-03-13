package com.project.tankerservice.model;

public enum Type {
    PUBLIC("PUBLIC"),SUPPLIER("SUPPLIER"),OWNED("OWN");

    private final String name;

    Type(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
