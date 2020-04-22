package ru.server.prerevolutiontinderserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.server.prerevolutiontinderserver.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private AuthService serviceAuth;


	@Autowired
	public AuthController(AuthService service) {
		this.serviceAuth = service;
	}

	@PostMapping(value = "")
	public Integer auth(@RequestBody String username) {
		logger.debug("Попытка авторизации, username: {}", username);
		return serviceAuth
				.auth(username);
	}
}
