package ru.server.prerevolutiontinderserver.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.repos.api.MatchRepository;
import ru.server.prerevolutiontinderserver.repos.api.ProfileRepository;
import ru.server.prerevolutiontinderserver.repos.api.UserRepository;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;


@DataJpaTest
public class ProfileServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private MatchRepository matchRepository;

	private ProfileService service;
	private User user1;
	private User user2;
	private Profile profile1;
	private Profile profile2;

	@BeforeEach
	public void init() {
		profile1 = new Profile("name", "desc", "сударь");
		profile2 = new Profile("name1", "desc1", "сударыня");
		profileRepository.save(profile1);
		profileRepository.save(profile2);
		user1 = new User("login", "pswd", 123, profile1, new ArrayList<>());
		user2 = new User("login1", "pswd1", "login1pswd1".hashCode(), profile2, new ArrayList<>());
		userRepository.save(user1);
		userRepository.save(user2);
		service = new ProfileService(userRepository, profileRepository, matchRepository);
	}

	//работает если запустить этот тестовый класс отдельно.
	//почему не проходит при сборке мавена - не разобрался

	@Test
	public void getProfile() {
		assertThat(service.getProfile(user1, 1L)).isEqualTo(user2.getProfile());
		assertThat(service.getProfile(user1, 4L)).isEqualTo(null);
	}

}