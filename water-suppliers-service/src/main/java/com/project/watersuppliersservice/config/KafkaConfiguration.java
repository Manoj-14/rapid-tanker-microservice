package com.project.watersuppliersservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class KafkaConfiguration {

    @Value("https://grown-pig-12240-us1-kafka.upstash.io:9092")
    private String bootStrapServer;
    @Bean
    public ProducerFactory<String,String> producerFactory(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        props.put("sasl.mechanism","SCRAM-SHA-256");
        props.put("sasl.jaas.config","org.apache.kafka.common.security.scram.ScramLoginModule required username=\"Z3Jvd24tcGlnLTEyMjQwJPhQ31N9CZtdQ-7JBMHYxnGrpwFFyU6c5oOBx9hUp7Y\" password=\"ZTU5YjdjMTEtOGI0Yy00NDU1LTk1MWMtODAzNjM5MjM3NTY4\";");
        props.put("security.protocol","SASL_SSL");

        return  new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic demoTopic(){
        return TopicBuilder.name("demoTopic").partitions(6).replicas(3).build();
    }
}
