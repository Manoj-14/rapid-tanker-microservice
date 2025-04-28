package com.project.orderservice.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message) {
        super("Order Id "+message+" not found");
    }
}
