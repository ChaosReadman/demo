package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Controller
public class LoginFeatureController {
    @Autowired
    AccountRepository accountr;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/adminuser")
    public String adminUser(@AuthenticationPrincipal User user, @ModelAttribute Account account, BindingResult bind) {
        ControllerCommon.setAccountInfo(user, account);

        return ("adminuser");
    }

    @GetMapping("/modifyuser")
    public String modifyUser(
            @AuthenticationPrincipal User user,
            @ModelAttribute Account account,
            @ModelAttribute(name = "modAccount") Account modAccount,
            @RequestParam(name = "id") int id) {
        ControllerCommon.setAccountInfo(user, account);

        if (id == -1) {
            modAccount.setId(-1);
        } else {
            Optional<Account> tmpAccount = accountr.findById(id);
            modAccount.setUserName(tmpAccount.get().getUserName());
            modAccount.setNickName(tmpAccount.get().getNickName());
            modAccount.setPassword("");
            modAccount.setAge(tmpAccount.get().getAge());
            modAccount.setPrivileges(tmpAccount.get().getPrivileges());
        }

        return ("modifyuser");
    }

    @PostMapping("updateuser")
    public String updateuser(@AuthenticationPrincipal User user, @ModelAttribute Account account,
            @ModelAttribute(name = "modAccount") @Validated Account modAccount, BindingResult bind) {
        ControllerCommon.setAccountInfo(user, account);
        if (bind.hasErrors()) {
            return "modifyuser";
        }
        modAccount.setLastUpdateUser(account.getUserName());
        modAccount.setPassword(encoder.encode(modAccount.getOrgPassword()));
        if (modAccount.getId() == -1) {
            accountr.insert(modAccount);
        } else {
            accountr.update(modAccount);
        }

        modAccount.setOrgPassword("");
        // もし自分と同じIDなら一度ログアウトが必要（なのでlogin画面に遷移）
        if (modAccount.getId() != -1 && account.getId() == modAccount.getId()) {
            return "login";
        }
        return "adminuser";
    }

    @GetMapping("/deleteuser")
    public String deleteuser(
            @AuthenticationPrincipal User user,
            @ModelAttribute Account account,
            BindingResult bind,
            @RequestParam(name = "id") int id) {
        ControllerCommon.setAccountInfo(user, account);
        if (accountr.findAll().size() == 1) {
            // 最後の一人なので削除不可能
            // 他にも、Privilegeとともに、findしないとだめかも
            // まだPrivilegeの役割を決めていないので今はこのままにしておく
            bind.rejectValue("id", "validation.deletion-of-account-impossible");
            return "adminuser";
        }
        accountr.deleteById(id);
        if (id == account.getId()) {
            return "login";
        }
        return "adminuser";
    }
}
