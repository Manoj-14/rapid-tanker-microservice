package com.project.orderservice.emitters;

import com.project.orderservice.modal.WaterOrder;
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

    public void sendForPayment(WaterOrder waterOrder){
        Map<String,Object > data = new HashMap<>();
        data.put("order", waterOrder);
        kafkaTemplate.send("order","PAYMENT_REQUEST",data);
    }
}
