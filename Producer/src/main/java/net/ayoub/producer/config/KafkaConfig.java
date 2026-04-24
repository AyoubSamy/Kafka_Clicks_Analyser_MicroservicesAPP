package net.ayoub.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    //here we create our tow topics automaticcly when the application go to run
    @Bean
    public NewTopic userClicksTopic() {
        return TopicBuilder.name("click")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic clickCountsTopic() {
        return TopicBuilder.name("click-counts")
                .partitions(1)
                .replicas(1)
                .build();
    }
}