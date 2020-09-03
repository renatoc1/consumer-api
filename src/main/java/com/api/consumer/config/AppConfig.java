package com.api.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	@Bean
	public WebClient webClientCampanha(WebClient.Builder builder) {
		return builder
				.baseUrl("https://campanha-api.herokuapp.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	
	@Bean
	public WebClient webClientSocioTorcedor(WebClient.Builder builder) {
		return builder
			.baseUrl("https://socio-torcedor-api.herokuapp.com")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
	
}
