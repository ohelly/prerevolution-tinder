package ru.server.prerevolutiontinderserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.server.prerevolutiontinderserver.entity.Match;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.repos.api.MatchRepository;
import ru.server.prerevolutiontinderserver.repos.api.ProfileRepository;
import ru.server.prerevolutiontinderserver.repos.api.UserRepository;

@Component
public class ProfileService {

	private final Logger logger = LoggerFactory.getLogger(ProfileService.class);

	private UserRepository userRepository;
	private ProfileRepository profileRepository;
	private MatchRepository matchRepository;

	@Autowired
	public ProfileService(UserRepository userRepository,
						  ProfileRepository profileRepository, MatchRepository matchRepository) {
		this.userRepository = userRepository;
		this.profileRepository = profileRepository;
		this.matchRepository = matchRepository;
	}

	public Profile getProfile(User user, Long id) {
		logger.debug("Получение анкеты с id: {}, пользователем: {}", id, user);
		if (user != null)
			return profileRepository.findProfileByIdLimitOneForUser(id, user.getProfile().getSex());
		return profileRepository.findProfileByIdLimitOne(id);
	}

	public Profile setChoice(String id, User user, Long choice) {
		logger.debug("id: {}, user: {}, choice: {}", id, user, choice);
		logger.debug("Попытка поставить лайк/дизлайк");
		Match match = matchRepository.findByIdProfileToMatch(user.getId(), Long.valueOf(id));
		if (match == null) {
			user.getMatches().add(new Match(Long.valueOf(id), choice));
			userRepository.save(user);
		} else {
			match.setChoice(choice);
		}
		return user.getProfile();
	}

	public Profile setAction(User user, String id, Long choice) {
		if (user != null && id != null) {
			return setChoice(id, user, choice);
		}
		return null;
	}

	public String getMatch(User user, Long id) {
		logger.debug("user: {}, id: {}", user, id);
		logger.debug("Получение матча");
		if (user != null) {
			return matchRepository
					.findMatch(userRepository
							.findByProfileId(id).getId(), user.getProfile().getId());
		}
		return null;
	}
}
