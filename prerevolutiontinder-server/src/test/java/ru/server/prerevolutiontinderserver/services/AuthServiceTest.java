package ru.server.prerevolutiontinderserver.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import ru.server.prerevolutiontinderserver.entity.Profile;
import ru.server.prerevolutiontinderserver.entity.User;
import ru.server.prerevolutiontinderserver.repos.api.UserRepository;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class AuthServiceTest {

	@Autowired
	private UserRepository repository;

	private AuthService service;
	private HttpHeaders emptyHeaders;
	private HttpHeaders invalidHeadersWithNull;
	private HttpHeaders invalidHeaders;
	private HttpHeaders correctHeaders;
	private User uncorrectedUser;
	private User correctedUser;

	@BeforeEach
	void init() {
		uncorrectedUser = new User("login", "pswd", 123, new Profile("name", "desc", "сударь"), new ArrayList<>());
		correctedUser = new User("login1", "pswd1", "login1pswd1".hashCode(), new Profile("name", "desc", "сударь"), new ArrayList<>());
		repository.save(uncorrectedUser);
		repository.save(correctedUser);
		emptyHeaders = new HttpHeaders();
		invalidHeadersWithNull = new HttpHeaders();
		invalidHeadersWithNull.set("Authorization", null);
		invalidHeaders = new HttpHeaders();
		invalidHeaders.set("Authorization", "000");
		correctHeaders = new HttpHeaders();
		correctHeaders.set("Authorization", String.valueOf("login1pswd1".hashCode()));
		service = new AuthService(repository);
	}


	@Test
	public void auth() {
		assertThat(service.auth("loginpswd")).isEqualTo(null);
		assertThat(service.auth("login1pswd1")).isEqualTo(correctedUser.getToken());
	}

	@Test
	public void checkAuth() {
		assertThat(service.checkAuth(emptyHeaders)).isEqualTo(null);
		assertThat(service.checkAuth(invalidHeadersWithNull)).isEqualTo(null);
		assertThat(service.checkAuth(invalidHeaders)).isEqualTo(null);
		assertThat(service.checkAuth(correctHeaders)).isEqualTo(correctedUser);
	}

	@Test
	public void getTokenFromRepository() {
		assertThat(service.getTokenFromRepository("login1pswd1")).isEqualTo("login1pswd1".hashCode());
		assertThat(service.getTokenFromRepository("null")).isEqualTo(null);
	}
}