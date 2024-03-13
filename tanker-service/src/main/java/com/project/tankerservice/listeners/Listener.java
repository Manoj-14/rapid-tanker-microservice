package com.project.tankerservice.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.tankerservice.dto.SuppliersDTO;
import com.project.tankerservice.model.TankerSupplier;
import com.project.tankerservice.service.TankerSupplierService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.modelmapper.ModelMapper;
import java.util.Map;

@Component
public class Listener {

    private  final TankerSupplierService tankerSupplierService;

    @Autowired
    public Listener(TankerSupplierService tankerSupplierService){
        this.tankerSupplierService = tankerSupplierService;
    }


    @KafkaListener(topics = "tanker-supplier",groupId = "tanker",errorHandler = "externalErrorHandler")
    public TankerSupplier hasPrivateTankers(Map<String,Object> data){
        ModelMapper mapper = new ModelMapper();
        System.out.println(data.get("supplier"));
        TankerSupplier tankerSupplier = mapper.map(data.get("supplier"),TankerSupplier.class);
        return tankerSupplierService.register(tankerSupplier,true);

    }


}
