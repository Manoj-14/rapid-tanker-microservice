package com.project.watersuppliersservice.emitters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.watersuppliersservice.model.WaterSuppliers;
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

    public void registerPrivateTankers(WaterSuppliers suppliers){
        Map<String,Object > data = new HashMap<>();
        data.put("supplier",suppliers);
        kafkaTemplate.send("tanker-supplier","supplier",data);
        kafkaTemplate.send("notification","supplier",data);
    }

}
