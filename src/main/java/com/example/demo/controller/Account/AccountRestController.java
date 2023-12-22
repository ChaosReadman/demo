package com.example.demo.controller.Account;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AccountRestController {
    @Autowired
    AccountRepository accountr;

    @GetMapping("/getusers")
    public ResponseEntity<?> getUsers() {
        ArrayList<Account> mblTmp = (ArrayList<Account>) accountr.findAll();
        ObjectMapper mapper = new ObjectMapper();

        try {
            return ResponseEntity.ok().body(mapper.writeValueAsString(mblTmp));
        } catch (JsonGenerationException | JsonMappingException e) {
            // catch various errors
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
