package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    String hello(){
        return ("hello");
    }
    @GetMapping("/support")
    String support(){
        return ("support");
    }
    @GetMapping("/login")
    String login(){
        return ("login");
    }
}
