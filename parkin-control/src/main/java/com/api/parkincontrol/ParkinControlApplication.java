package com.api.parkincontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class ParkinControlApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParkinControlApplication.class,args);
	}
}

@RestController
class MyControler{
	@GetMapping("/hello")
	public String index(){
		return "Olá mundo!!!";
	}
}