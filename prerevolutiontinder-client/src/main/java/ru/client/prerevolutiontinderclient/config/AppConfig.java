package ru.client.prerevolutiontinderclient.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

	@Bean
	public HttpHeaders httpHeaders() {
		return new HttpHeaders();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.messageConverters(messageConverter())
				.build();
	}

	@Bean
	public List<HttpMessageConverter<?>> messageConverter() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		StringHttpMessageConverter converter1 = new StringHttpMessageConverter();
		converter1.setDefaultCharset(StandardCharsets.UTF_8);
		messageConverters.add(converter1);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		return messageConverters;
	}
}
