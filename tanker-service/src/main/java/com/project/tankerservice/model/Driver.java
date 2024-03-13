package com.project.tankerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Driver {
    @Id
    private UUID id;
    private String Name;
    private String number;
}
