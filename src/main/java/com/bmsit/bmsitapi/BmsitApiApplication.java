package com.bmsit.bmsitapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BmsitApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsitApiApplication.class, args);
	}
	@GetMapping(value = "/")
	public ResponseEntity<String> welcome(){
		return ResponseEntity.ok("Welcome to BMSIT API");
	}

}
