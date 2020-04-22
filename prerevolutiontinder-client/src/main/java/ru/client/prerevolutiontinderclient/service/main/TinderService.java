package ru.client.prerevolutiontinderclient.service.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public abstract class TinderService {

	@Autowired
	public HttpHeaders headers;

	protected <T> HttpEntity httpEntity(T body) {
		return new HttpEntity<>(body, headers);
	}

	protected void setParamHeaders(String headersName, String param) {
		headers.add(headersName, param);
	}

}
