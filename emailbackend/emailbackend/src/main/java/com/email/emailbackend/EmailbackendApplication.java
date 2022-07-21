package com.email.emailbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EmailbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailbackendApplication.class, args);
	}

}
