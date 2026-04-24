package net.ayoub.producer.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired //dependancy injection for automaticly inejct the configuration into the kafkatemplate class
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendClickEvent(String userId) {
        String message = userId + ",click";
        kafkaTemplate.send("click", userId, message); // send the message to the kafka topic
    }
}
