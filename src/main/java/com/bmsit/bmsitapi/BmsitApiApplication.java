package com.bmsit.bmsitapi;

import com.bmsit.bmsitapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@EnableAspectJAutoProxy
@RestController
@SpringBootApplication
public class BmsitApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsitApiApplication.class, args);
	}
	@GetMapping(value = "/")
	public ResponseEntity<String> welcome(){
		return ResponseEntity.ok("Welcome to BMSIT API");
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
