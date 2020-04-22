package ru.client.prerevolutiontinderclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.client.prerevolutiontinderclient.formatter.OutputProfileDescription;
import ru.client.prerevolutiontinderclient.messages.Messages;
import ru.client.prerevolutiontinderclient.pojo.Profile;
import ru.client.prerevolutiontinderclient.service.main.TinderService;

import java.util.ArrayList;
import java.util.List;

@Component(value = "matchesService")
@ConfigurationProperties(prefix = "endpoint.matches")
public class MatchesService extends TinderService {

	private final Logger logger = LoggerFactory.getLogger(MatchesService.class);

	@Autowired
	private ServiceRequests requests;

	private String url;
	private List<Profile> matches = new ArrayList<>();

	public String showMatches(String num) {
		logger.debug("Попытка показать матч номер: {}", num);
		try {
			if (num == null) {
				if ((matches = getMatches()) != null) {
					logger.debug("Матчи найден");
					return previewMatches(matches);
				}
				logger.debug("Необходимо авторизоваться");
				return Messages.NEEDAUTH.getMessages();
			}
			return showOneMatch(Integer.valueOf(num));
		} catch (NumberFormatException e) {
			logger.debug("Неправильно введеные данные");
			return Messages.INVALIDNUM.getMessages();
		}
	}

	private List<Profile> getMatches() {
		logger.debug("Запрос на список матчей");
		ResponseEntity<List<Profile>> response = requests.getMatches(url, httpEntity(null));
		if (response.getBody() != null) {
			logger.debug("Успешно");
			return response.getBody();
		}
		logger.debug("Ответ от сервера пуст");
		return null;
	}

	private String previewMatches(List<Profile> profiles) {
		if (profiles.size() == 0) {
			return Messages.EMPTYMATCH.getMessages();
		}
		return OutputProfileDescription.preview(profiles);
	}

	private String showOneMatch(Integer num) {
		if ((matches = getMatches()) != null) {
			if (matches.size() != 0) {
				if (num > 0 && num <= matches.size()) {
					return OutputProfileDescription.out(matches.get(num - 1));
				}
				return Messages.INVALIDNUM.getMessages();
			}
			return Messages.EMPTYMATCH.getMessages();
		}
		return Messages.NEEDAUTH.getMessages();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
