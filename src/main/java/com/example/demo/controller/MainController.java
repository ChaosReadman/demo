package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Account;
import com.example.demo.model.UserInfo;
import com.example.demo.model.information;
import com.example.demo.model.link;
import com.example.demo.model.messageBoard;
import com.example.demo.repository.messageBoardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MainController {
    @Autowired
    messageBoardRepository mbr;

    void setAccountInfo(User user, Account account) {
        ObjectMapper mapper = new ObjectMapper();
        List<Account> accounts = new ArrayList<Account>();
        try {
            accounts = mapper.readValue(user.getAuthorities().toString(), new TypeReference<List<Account>>() {
            });
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Account a = accounts.get(0);
        account.setUserName(a.getUserName());
        account.setNickName(a.getNickName());
    }

    @GetMapping("/")
    String index(@AuthenticationPrincipal User user, @ModelAttribute Account account) {
        // @AuthenticationPrincipal で user にキャストされた情報が入ってくるので、Accountへ情報を渡し、画面で表示する
        setAccountInfo(user, account);
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

    @PostMapping("/logout")
    public String performLogout() {
        // .. perform logout
        return "login";
    }

    @GetMapping("/form")
    private String inputForm(@AuthenticationPrincipal User user, @ModelAttribute UserInfo userInfo,
            @ModelAttribute Account account) {
        setAccountInfo(user, account);

        return ("inputForm");
    }

    @PostMapping("/form")
    private String confirmForm(@AuthenticationPrincipal User user, @ModelAttribute UserInfo userInfo,
            @ModelAttribute Account account) {
        setAccountInfo(user, account);
        return ("confirm");
    }

    @GetMapping("/messageboard")
    public String messageBoard(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("mb") messageBoard mb) {
        setAccountInfo(user, account);
        mb.setUserName(account.getNickName());

        return ("messageboard");
    }

    @GetMapping("/informationEdit")
    public String information(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("info") information info) {
        setAccountInfo(user, account);

        return ("informationEdit");
    }

    @GetMapping("/link")
    public String link(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("lnk") link lnk) {
        setAccountInfo(user, account);

        return ("link");
    }

    @GetMapping("/linkEdit")
    public String linkEdit(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("lnk") link lnk) {
        setAccountInfo(user, account);

        return ("linkEdit");
    }

    @GetMapping("/adminuser")
    public String adminUser(@AuthenticationPrincipal User user, @ModelAttribute Account account) {
        setAccountInfo(user, account);

        return ("adminuser");
    }

}
