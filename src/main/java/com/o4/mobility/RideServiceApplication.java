package com.o4.mobility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class RideServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideServiceApplication.class, args);
	}

}
