package ru.server.prerevolutiontinderserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.repos.api.ProfileRepository;
import ru.server.prerevolutiontinderserver.repos.api.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchesService {

	private final Logger logger = LoggerFactory.getLogger(MatchesService.class);

	private ProfileRepository repository;

	@Autowired
	public MatchesService(ProfileRepository repository) {
		this.repository = repository;
	}

	public List<Profile> allMatches(User user) {
		logger.debug("Получение списка матчей пользователем: {}", user);
		if (user != null) {
			return repository.findMatchesProfile(user.getProfile().getId());
		}
		return null;
	}
}
