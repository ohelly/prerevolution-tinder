package ru.server.prerevolutiontinderserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.services.AuthService;
import ru.server.prerevolutiontinderserver.services.UserService;

import javax.persistence.PostUpdate;


@RestController
@RequestMapping("/profiles")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService service;
	private final AuthService authService;

	@Autowired
	public UserController(UserService service, AuthService authService) {
		this.service = service;
		this.authService = authService;
	}

	@PostMapping(value = "")
	public User add(@RequestBody User user) {
		logger.debug("Попытка добавления нового пользователя: {}", user);
		return service
				.addUser(user);
	}

	@PutMapping(value = "")
	public User edit(@RequestHeader HttpHeaders headers, @RequestBody String desc) {
		logger.debug("Поптыка изменить анкету");
		User user = authService.checkAuth(headers);
		logger.debug("Пользователем: {}", user);
		return service
				.editProfile(user, desc);
	}

	@DeleteMapping(value = "")
	public String delete(@RequestHeader HttpHeaders headers) {
		logger.debug("Попытка удаления анкеты");
		User user = authService.checkAuth(headers);
		logger.debug("пользователем: {}", user);
		return service
				.delete(user);
	}
}
