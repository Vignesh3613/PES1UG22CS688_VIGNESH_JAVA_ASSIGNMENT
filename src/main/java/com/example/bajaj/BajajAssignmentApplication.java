package com.example.bajaj;

import com.example.bajaj.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BajajAssignmentApplication implements CommandLineRunner {

	@Autowired
	private WebhookService webhookService;

	public static void main(String[] args) {
		SpringApplication.run(BajajAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) {
		webhookService.startChallenge();
	}
}
