package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.messageBoard;
import com.example.demo.repository.messageBoardRepository;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private messageBoardRepository msgr;

	@BeforeEach
	void init() {

	}

	@Test
	void contextLoads() {
	}

	@Test
	void testMSG() {
		messageBoard mb = new messageBoard();
		mb.setUserName("user1");
		mb.setMessage("test");
		msgr.insert(mb);
		System.out.println(mb.toString());
	}
}
