package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Account;
import com.example.demo.model.UserInfo;
import com.example.demo.model.exinformation;
import com.example.demo.model.information;
import com.example.demo.model.link;
import com.example.demo.model.messageBoard;
import com.example.demo.repository.messageBoardRepository;

@Controller
public class MainController {
    @Autowired
    messageBoardRepository mbr;

    @GetMapping("/")
    String index(@AuthenticationPrincipal User user, @ModelAttribute Account account) {
        // @AuthenticationPrincipal で user にキャストされた情報が入ってくるので、Accountへ情報を渡し、画面で表示する
        ControllerCommon.setAccountInfo(user, account);
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
        ControllerCommon.setAccountInfo(user, account);

        return ("inputForm");
    }

    @PostMapping("/form")
    private String confirmForm(@AuthenticationPrincipal User user, @ModelAttribute UserInfo userInfo,
            @ModelAttribute Account account) {
        ControllerCommon.setAccountInfo(user, account);
        return ("confirm");
    }

    @GetMapping("/messageboard")
    public String messageBoard(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("mb") messageBoard mb) {
        ControllerCommon.setAccountInfo(user, account);
        mb.setUserName(account.getNickName());

        return ("messageboard");
    }

    @GetMapping("/informationEdit")
    public String information(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("info") information info) {
        ControllerCommon.setAccountInfo(user, account);

        return ("informationEdit");
    }

    @GetMapping("/link")
    public String link(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("lnk") link lnk) {
        ControllerCommon.setAccountInfo(user, account);

        return ("link");
    }

    @GetMapping("/linkEdit")
    public String linkEdit(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("lnk") link lnk) {
        ControllerCommon.setAccountInfo(user, account);

        return ("linkEdit");
    }

    @GetMapping("/exinformationEdit")
    public String exinformation(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("exinfo") exinformation exinfo) {
        ControllerCommon.setAccountInfo(user, account);

        return ("exinformationEdit");
    }
}
