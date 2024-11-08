package com.misight.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.misight.client.ui.MainMenu;
import com.misight.client.auth.AuthenticationService;
import com.misight.client.service.setup.*;
import com.misight.client.service.operation.*;
import com.misight.client.service.reporting.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			AuthenticationService authService = ctx.getBean(AuthenticationService.class);
			MainMenu mainMenu = ctx.getBean(MainMenu.class);
			mainMenu.start();
		};
	}
}