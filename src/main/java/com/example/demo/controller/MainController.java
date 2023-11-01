package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserInfo;


@Controller
public class MainController {
    @GetMapping("/")
    String index(@AuthenticationPrincipal User user, Model model){
        System.out.println(user.getUsername());
        model.addAttribute("userName",user.getUsername());
        return ("index");
    }
    @GetMapping("/support")
    String support(){
        return ("support");
    }
    @GetMapping("/login")
    String login(){
        return ("login");
    }
    @GetMapping("/form")
    private String inputForm(@ModelAttribute UserInfo userInfo){
        return "inputForm";
    }

    @PostMapping("/form")
    private String confirmForm(@ModelAttribute UserInfo userInfo){
        return "confirm";
    }
}
