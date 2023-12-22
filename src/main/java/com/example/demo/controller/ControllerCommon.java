package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;

import com.example.demo.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerCommon {
        public static void setAccountInfo(User user, Account account) {
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
        account.setId(a.getId());
    }
}
