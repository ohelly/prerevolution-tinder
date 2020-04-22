package ru.server.prerevolutiontinderserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.services.AuthService;
import ru.server.prerevolutiontinderserver.services.MatchesService;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

	private final Logger logger = LoggerFactory.getLogger(MatchController.class);

	private MatchesService service;
	private AuthService authService;

	@Autowired
	public MatchController(MatchesService service, AuthService authService) {
		this.service = service;
		this.authService = authService;
	}

	@GetMapping(value = "")
	public List<Profile> matches(@RequestHeader HttpHeaders headers) {
		logger.debug("Попытка получения списка матчей");
		User user = authService.checkAuth(headers);
		logger.debug("пользователем: {}", user);
		return service
				.allMatches(user);
	}
}
