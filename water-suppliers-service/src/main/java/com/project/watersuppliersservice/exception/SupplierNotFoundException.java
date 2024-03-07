package com.project.watersuppliersservice.exception;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String message){
        super(message);
    }
}
