package com.example.demo;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
    @Autowired
    private PasswordEncoder encoder;
    private final PrintStream standardOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(standardOut));
    }

    @Test
    public void encode()
    {
        System.out.println("pass:" + encoder.encode("pass"));
        System.out.println("password:" + encoder.encode("password"));
        //pass:$2a$10$9G7pysJ88hLbyjkUc.Frnu8W2pMaqPPGSRXVNDh6Y6OHwJ1Mtmk96
        //password:$2a$10$J6LDh3aYHgPtx5YYSvM6Zu.aRzIg4s03RFamgx1C8nmednbecZycm
    }
}
