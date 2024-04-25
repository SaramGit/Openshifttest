package com.nt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DummyKubernativeAppApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void checkEvenOrNot() {
		int num=4;
		assertEquals(2, 4/2);
	}

}
