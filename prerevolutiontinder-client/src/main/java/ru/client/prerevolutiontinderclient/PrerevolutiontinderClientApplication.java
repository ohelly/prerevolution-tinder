package ru.client.prerevolutiontinderclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ru.client.prerevolutiontinderclient.service.ProfileService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PrerevolutiontinderClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrerevolutiontinderClientApplication.class, args);
	}

	@Component
	public class Initializing {

		@Autowired
		ProfileService service;

		@PostConstruct
		public void init() {
			System.out.println(service.getProfile());
		}
	}
}
