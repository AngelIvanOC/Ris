package com.example.conexionbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ConexionbdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConexionbdApplication.class, args);
	}

}
