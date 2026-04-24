package net.ayoub.producer.web;

import net.ayoub.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClickController {
    @Autowired
    private KafkaProducerService producerService;

    @GetMapping("/")
    public String index() {
        return "index"; // Affiche la page index.html

    }

    @PostMapping("/click")
    @ResponseBody // ResponseBody because we dont want to redirect or go to another page we just want to show a message
    public String handleClick(@RequestParam String userId) {
        producerService.sendClickEvent(userId);
        return "Événement envoyé pour : " + userId;

    }

}
