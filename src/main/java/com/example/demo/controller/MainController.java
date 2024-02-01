package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Autowired
    messageBoardRepository mbr;

    @GetMapping("/")
    String index() {
        return ("index");
    }

    @GetMapping("/support")
    String support() {
        return ("support");
    }

    @GetMapping("/members/login")
    String login() {
        return ("/members/login");
    }

    @GetMapping("/members/")
    String membertop(@AuthenticationPrincipal User user, @ModelAttribute Account account) {
        // @AuthenticationPrincipal で user にキャストされた情報が入ってくるので、Accountへ情報を渡し、画面で表示する
        ControllerCommon.setAccountInfo(user, account);

        return ("/members/index");
    }

    @PostMapping("/members/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request,
            HttpServletResponse response) {
        // .. perform logout
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }

    @GetMapping("/members/form")
    private String inputForm(@AuthenticationPrincipal User user, @ModelAttribute UserInfo userInfo,
            @ModelAttribute Account account) {
        ControllerCommon.setAccountInfo(user, account);

        return ("/members/inputForm");
    }

    @PostMapping("/members/form")
    private String confirmForm(@AuthenticationPrincipal User user, @ModelAttribute UserInfo userInfo,
            @ModelAttribute Account account) {
        ControllerCommon.setAccountInfo(user, account);
        return ("/members/confirm");
    }

    @GetMapping("/members/messageboard")
    public String messageBoard(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("mb") messageBoard mb) {
        ControllerCommon.setAccountInfo(user, account);
        mb.setUserName(account.getNickName());

        return ("/members/messageboard");
    }

    @GetMapping("/members/informationEdit")
    public String information(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("info") information info) {
        ControllerCommon.setAccountInfo(user, account);

        return ("/members/informationEdit");
    }

    @GetMapping("/members/link")
    public String link(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("lnk") link lnk) {
        ControllerCommon.setAccountInfo(user, account);

        return ("/members/link");
    }

    @GetMapping("/members/linkEdit")
    public String linkEdit(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("lnk") link lnk) {
        ControllerCommon.setAccountInfo(user, account);

        return ("/members/linkEdit");
    }

    @GetMapping("/members/exinformationEdit")
    public String exinformation(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute("exinfo") exinformation exinfo) {
        ControllerCommon.setAccountInfo(user, account);

        return ("/members/exinformationEdit");
    }

    @GetMapping("/members/makePage")
    public String makePage(@AuthenticationPrincipal User user, @ModelAttribute Account account) {
        ControllerCommon.setAccountInfo(user, account);

        return ("/members/makePage");
    }
}
