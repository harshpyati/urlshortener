package com.harsh.urlshortener;

import com.harsh.urlshortener.repo.IdRepository;
import com.harsh.urlshortener.service.IdEncoderService;
import com.harsh.urlshortener.service.IdGenService;
import com.harsh.urlshortener.service.UrlShortenerService;
import com.harsh.urlshortener.service.impl.IdEncoderServiceImpl;
import com.harsh.urlshortener.service.impl.IdGenServiceImpl;
import com.harsh.urlshortener.service.impl.UrlShortenerServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UrlshortenerApplication {
    @Bean
    public UrlShortenerService getUrlShortenerService() {
        return new UrlShortenerServiceImpl();
    }

    @Bean
    public IdGenService getIdGenService() {
        return new IdGenServiceImpl();
    }

    @Bean
    public IdEncoderService getIdEncoderServiceImpl() {
        return new IdEncoderServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(UrlshortenerApplication.class, args);
    }

}
