package com.project.exceptionhandlingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ExceptionController {
    @KafkaListener(topics = "exceptions",groupId = "group1")
    public void consume(String message){

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
