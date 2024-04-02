package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@EnableAutoConfiguration()
//@ComponentScan

public class DummyKubernativeAppApplication {
	
	@GetMapping("/")
	public String getResponse() {
		return "Hello bandhu";
	}
   
	public static void main(String[] args) {
		SpringApplication.run(DummyKubernativeAppApplication.class, args);
	}

}
