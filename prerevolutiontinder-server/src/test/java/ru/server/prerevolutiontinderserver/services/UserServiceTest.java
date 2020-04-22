package ru.server.prerevolutiontinderserver.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.repos.api.UserRepository;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class UserServiceTest {

	@Autowired
	private UserRepository repository;

	private UserService service;
	private User user1;
	private User user2;
	private User user3;

	@BeforeEach
	public void init() {
		user1 = new User("login", "pswd", 123, new Profile("name", "desc", "сударь"), new ArrayList<>());
		user2 = new User("login", "pswd", 123, new Profile("name", "profile", "сударь"), new ArrayList<>());
		user3 = new User("login1", "pswd1", "login1pswd1".hashCode(), new Profile("name", "desc", "м"), new ArrayList<>());
		repository.save(user1);
		service = new UserService(repository);
	}

	@Test
	public void editProfile() {
		assertThat(service.editProfile(user1, "profile")).isEqualTo(user1);
		assertThat(service.editProfile(null, "profile")).isEqualTo(null);
	}

	@Test
	public void addUser() {
		assertThat(service.addUser(user2)).isEqualTo(user2);
		assertThat(service.addUser(user3)).isEqualTo(null);
	}

	@Test
	public void delete() {
		assertThat(service.delete(user1)).isEqualTo("DELETE");
		assertThat(service.delete(null)).isEqualTo(null);
	}

	@Test
	public void createToken() {
		assertThat(service.createToken("hash", "code")).isEqualTo("hashcode".hashCode());
	}

	@Test
	public void validationUser() {
		assertThat(service.validationUser(user1)).isEqualTo(true);
		assertThat(service.validationUser(user3)).isEqualTo(false);
	}
}