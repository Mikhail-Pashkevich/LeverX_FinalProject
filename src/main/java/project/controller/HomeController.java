package project.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
//    @GetMapping("/home")
//    public String home1() {
//        return "home";
//    }
}
