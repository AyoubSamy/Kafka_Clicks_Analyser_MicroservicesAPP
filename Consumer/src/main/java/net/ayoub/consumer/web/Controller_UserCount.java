package net.ayoub.consumer.web;

import net.ayoub.consumer.service.kafkaConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class Controller_UserCount {

    private final kafkaConsumerService consumerService;

    public Controller_UserCount(kafkaConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/clicks/count")
    public Map<String, String> getRealTimeCounts() {
        return consumerService.getLatestCounts();
    }
}