package ru.server.prerevolutiontinderserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.repos.api.UserRepository;


@Component
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public User editProfile(User user, String desc) {
		logger.debug("Изменение описания анкеты");
		if (user != null) {
			user.getProfile().setDescription(desc);
		}
		return user;
	}

	public User addUser(User user) {
		logger.debug("Добавление нового пользователя");
		if (validationUser(user)) {
			user.setToken(createToken(user.getLogin(), user.getPswd()));
			repository.save(user);
			return repository.findByToken(createToken(user.getLogin(), user.getPswd()));
		}
		return null;
	}

	public String delete(User user) {
		logger.debug("Удаление пользователя: {}", user);
		if (user != null) {
			repository.delete(user);
			return "DELETE";
		}
		return null;
	}

	public Integer createToken(String login, String pswd) {
		logger.debug("Генерация токена");
		return (login + pswd).hashCode();
	}

	public boolean validationUser(User user) {
		return user.getLogin() != null && user.getPswd() != null && validationProfile(user.getProfile());
	}

	public boolean validationProfile(Profile profile) {
		return profile.getDescription() != null && profile.getName() != null && profile.getSex() != null
				&& (profile.getSex().equals("сударь") || profile.getSex().equals("сударыня"));
	}
}
