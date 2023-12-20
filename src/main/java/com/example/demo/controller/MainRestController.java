package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.model.information;
import com.example.demo.model.link;
import com.example.demo.model.messageBoard;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.informationRepository;
import com.example.demo.repository.linkRepository;
import com.example.demo.repository.messageBoardRepository;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MainRestController {
    @Autowired
    messageBoardRepository mbr;

    @Autowired
    informationRepository infor;

    @Autowired
    linkRepository linkr;

    @Autowired
    AccountRepository accountr;

    @PostMapping("/insertmsg")
    public ResponseEntity<?> insertmsg(@RequestBody messageBoard mb) {
        if (!mb.getMessage().equals("")) {
            mbr.insert(mb);
            mb.setMessage("");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getmsg")
    public ResponseEntity<?> getmsg() {
        ArrayList<messageBoard> mblTmp = (ArrayList<messageBoard>) mbr.findAll();
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

    @PostMapping("/insertinfo")
    public ResponseEntity<?> insertinfo(@RequestBody information info) {
        if (!info.getMessage().equals("")) {
            infor.insert(info);
            info.setMessage("");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getinfo")
    public ResponseEntity<?> getinfo() {
        ArrayList<information> mblTmp = (ArrayList<information>) infor.findAll();
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

    @PostMapping("/insertlink")
    public ResponseEntity<?> insertlink(@RequestBody @Validated link lnk, BindingResult result) {
        if(result.hasErrors()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("errMsg", "テストテストテスト");
            HttpStatus status = HttpStatus.BAD_REQUEST;
            List<FieldError> resList = result.getFieldErrors();
            return new ResponseEntity<>(resList, httpHeaders, status);
        }
        else if (!lnk.getTitle().equals("") || !lnk.getUrl().equals("")) {
            linkr.insert(lnk);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getlink")
    public ResponseEntity<?> getlink() {
        ArrayList<link> mblTmp = (ArrayList<link>) linkr.findAll();
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

    @PostMapping("/deletelink")
    public ResponseEntity<?> deletelink(@RequestBody link lnk) {
        linkr.delete(lnk);
        return ResponseEntity.ok().build();
    }
    
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
