package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;

@Controller
public class FormController {
    
    @GetMapping("/form")
    private String inputForm(@ModelAttribute User user){
        return "inputForm";
    }

    @PostMapping("/form")
    private String confirmForm(@ModelAttribute User user){
        return "confirm";
    }
}
