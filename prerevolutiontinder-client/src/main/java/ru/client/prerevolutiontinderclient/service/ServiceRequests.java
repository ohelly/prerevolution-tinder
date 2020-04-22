package ru.client.prerevolutiontinderclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.client.prerevolutiontinderclient.pojo.Profile;
import ru.client.prerevolutiontinderclient.pojo.User;

import java.util.List;

@Component(value = "serviceRequests")
public class ServiceRequests {

	private final Logger logger = LoggerFactory.getLogger(ServiceRequests.class);

	private RestTemplate template;

	@Autowired
	public ServiceRequests(RestTemplate template) {
		this.template = template;
	}

	public ResponseEntity<Integer> login(String url, HttpEntity body) {
		logger.debug("exchange login: url = {}, body = {}", url, body);
		return template
				.exchange(url, HttpMethod.POST, body, Integer.class);
	}

	public ResponseEntity<List<Profile>> getMatches(String url, HttpEntity body) {
		logger.debug("exchange getMatches: url = {}, body = {}", url, body);
		return template
				.exchange(url, HttpMethod.GET, body,
						new ParameterizedTypeReference<List<Profile>>() {
						});
	}

	public ResponseEntity<Profile> getProfile(String url, Long id, HttpEntity body) {
		logger.debug("exchange getProfile: url = {}, id = {}, body = {}", url, id, body);
		return template
				.exchange(url + "/" + id, HttpMethod.GET, body, Profile.class);
	}

	public ResponseEntity<Profile> swipe(String url, Long id, HttpEntity body, String choice) {
		logger.debug("exchange swipe: url = {}, id = {}, body = {}, choice = {}", url, id, body, choice);
		return template
				.exchange(url + "/" + id + "/" + choice, HttpMethod.POST, body, Profile.class);
	}

	public ResponseEntity<String> haveMatch(String url, Long id, HttpEntity body) {
		logger.debug("exchange haveMatch: url = {}, id = {}, body = {}", url, id, body);
		return template
				.exchange(url + "/" + id + "/match", HttpMethod.GET, body, String.class);
	}

	public ResponseEntity<User> createUser(String url, HttpEntity body) {
		logger.debug("exchange createUser: url = {}, body = {}", url, body);
		return template.exchange(url, HttpMethod.POST, body, User.class);
	}

	public ResponseEntity<User> edit(String url, HttpEntity body) {
		logger.debug("exchange edit: url = {}, body = {}", url, body);
		return template
				.exchange(url, HttpMethod.PUT, body, User.class);
	}

	public ResponseEntity<String> delete(String url, HttpEntity body) {
		logger.debug("exchange delete: url = {}, body = {}", url, body);
		return template
				.exchange(url, HttpMethod.DELETE, body, String.class);
	}
}
