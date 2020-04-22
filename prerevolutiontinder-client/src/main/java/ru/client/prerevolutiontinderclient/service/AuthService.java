package ru.client.prerevolutiontinderclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import ru.client.prerevolutiontinderclient.messages.Messages;
import ru.client.prerevolutiontinderclient.service.main.TinderService;

@Component(value = "authService")
@ConfigurationProperties(prefix = "endpoint.auth")
public class AuthService extends TinderService {

	private final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private String url;
	private ProfileService service;
	private ServiceRequests requests;

	@Autowired
	public AuthService(ProfileService service, ServiceRequests requests) {
		this.service = service;
		this.requests = requests;
	}

	public String login(String username, String pswd) {
		logger.debug("Поыптка авторизации, username и pswd: {}", username + pswd);
		ResponseEntity<Integer> response = requests.login(url, httpEntity(username + pswd));
		if (response.getBody() != null) {
			setParamHeaders("Authorization", response.getBody().toString());
			service.reset();
			logger.debug("Авторизация прошла успешно");
			return Messages.SUCCESS.getMessages() + service.getProfile();
		}
		logger.debug("Не удалось авторизоваться");
		return Messages.FAILED.getMessages();
	}

	public String logOut() {
		service.reset();
		headers = new HttpHeaders();
		return service.getProfile();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
