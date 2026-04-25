package net.ayoub.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class kafkaConsumerService {
    // ConcurrentHashMap est crucial ici pour gérer les accès simultanés (Kafka vs API)
    private final Map<String, String> clickStats = new ConcurrentHashMap<>();

    @KafkaListener(topics = "click-counts", groupId = "analytics-group-v2")
    public void consume(
            @Header(KafkaHeaders.RECEIVED_KEY) String userId,
            String count) {

        if (count == null || count.isEmpty()) {
            count = "0"; // or skip if you prefer
        }

        clickStats.put(userId, count);

        System.out.println("User: " + userId + " | Total: " + count);
    }

    public Map<String, String> getLatestCounts() {
        return clickStats;
    }
}
