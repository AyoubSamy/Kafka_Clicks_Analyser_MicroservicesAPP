package net.ayoub.consumer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Vue_Controller {
    @GetMapping("/")
    public String home() {
        return "indexconsumer";
    }
}
