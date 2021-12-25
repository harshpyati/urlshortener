package com.harsh.urlshortener;

import com.harsh.urlshortener.service.UrlShortenerService;
import com.harsh.urlshortener.service.impl.UrlShortenerServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UrlshortenerApplication {

	@Bean
	public UrlShortenerService getUrlShortenerService(){
		return new UrlShortenerServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(UrlshortenerApplication.class, args);
	}

}
