package ru.server.prerevolutiontinderserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.repos.api.UserRepository;

import java.util.Objects;

@Component
public class AuthService {

	private final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private UserRepository repository;

	@Autowired
	public AuthService(UserRepository repository) {
		this.repository = repository;
	}

	public Integer auth(String username) {
		logger.debug("Авторизация пользователя: {}", username);
		if (username != null) {
			return getTokenFromRepository(username);
		}
		return null;
	}

	public Integer getTokenFromRepository(String username) {
		logger.debug("Получение токена: {}", username);
		User user = repository.findByToken(username.hashCode());
		if (user != null) {
			logger.debug("Пользователем: {}", user);
			return user.getToken();
		}
		logger.debug("Пользователь не найден: {}", username);
		return null;
	}

	public User checkAuth(HttpHeaders headers) {
		logger.debug("Проверка авторизации пользователя");
		if (headers != null && headers.containsKey("Authorization")) {
			Integer token = getTokenFromHeaders(headers);
			if (token != null) {
				return repository.findByToken(token);
			}
		}
		return null;
	}

	private Integer getTokenFromHeaders(HttpHeaders headers) {
		logger.debug("Получение токена из хедера");
		try {
			return Integer
					.valueOf(Objects.requireNonNull(headers.getFirst("Authorization")));
		} catch (NumberFormatException | NullPointerException e) {
			return null;
		}
	}

}
