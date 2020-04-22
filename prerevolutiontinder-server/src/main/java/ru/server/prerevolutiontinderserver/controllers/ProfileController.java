package ru.server.prerevolutiontinderserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.services.AuthService;
import ru.server.prerevolutiontinderserver.services.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

	private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	private final long dislike = 0;
	private final long like = 1;
	private ProfileService serviceProfile;
	private AuthService authService;

	@Autowired
	public ProfileController(ProfileService service, AuthService authService) {
		this.serviceProfile = service;
		this.authService = authService;
	}

	@PostMapping("/{id}/dislike")
	public Profile setDislike(@RequestHeader HttpHeaders headers, @PathVariable String id) {
		logger.debug("Попытка поставить дизлайк");
		User user = authService.checkAuth(headers);
		logger.debug("Пользователем: {}", user);
		logger.debug("Анкете с id: {}", id);
		return serviceProfile
				.setAction(user, id, dislike);
	}

	@PostMapping("/{id}/like")
	public Profile setLike(@RequestHeader HttpHeaders headers, @PathVariable String id) {
		logger.debug("Попытка поставить лайк");
		User user = authService.checkAuth(headers);
		logger.debug("Пользователем: {}", user);
		logger.debug("Анкете с id: {}", id);
		return serviceProfile
				.setAction(user, id, like);
	}

	@GetMapping("/{id}")
	public Profile getProfile(@RequestHeader HttpHeaders headers, @PathVariable String id) {
		logger.debug("Получение анкеты id: {}", id);
		User user = authService.checkAuth(headers);
		logger.debug("Пользователем: {}", user);
		return serviceProfile
				.getProfile(user, Long.valueOf(id));
	}

	@GetMapping("/{id}/match")
	public String getMatch(@RequestHeader HttpHeaders headers, @PathVariable String id) {
		logger.debug("Получение матчаб с анкетой id: {}", id);
		User user = authService.checkAuth(headers);
		logger.debug("Пользователем: {}", user);
		return serviceProfile
				.getMatch(user, Long.valueOf(id));
	}
}
