package com.project.userservice.emitters;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Emitters {

    private final KafkaTemplate<String,Map<String,Object>> kafkaTemplate;

    @Autowired
    public Emitters(KafkaTemplate<String,Map<String,Object>> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void registerUser(UserDTO user){
        Map<String,Object > data = new HashMap<>();
        data.put("user",user);
        kafkaTemplate.send("user-service","user",data);
    }
}
