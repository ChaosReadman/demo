package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Account;
import com.example.demo.model.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MainController {

    void setUserInfo(User user, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        List<Account> account = new ArrayList<Account>();
        try {
            account = mapper.readValue(user.getAuthorities().toString(), new TypeReference<List<Account>>() {
            });
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("nickName", account.get(0).getNickName());
    }

    @GetMapping("/")
    String index(@AuthenticationPrincipal User user, Model model) {
        // @AuthenticationPrincipal で user にキャストされた情報が入ってくるので、Modelへ情報を渡し、画面で表示する
        setUserInfo(user, model);
        return ("index");
    }

    @GetMapping("/support")
    String support() {
        return ("support");
    }

    @GetMapping("/login")
    String login() {
        return ("login");
    }

    @GetMapping("/form")
    private String inputForm(@AuthenticationPrincipal User user, @ModelAttribute UserInfo userInfo, Model model) {
        setUserInfo(user, model);
        return "inputForm";
    }

    @PostMapping("/form")
    private String confirmForm(@AuthenticationPrincipal User user, @ModelAttribute UserInfo userInfo, Model model) {
        setUserInfo(user, model);
        return "confirm";
    }
}
