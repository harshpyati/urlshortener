package com.harsh.urlshortener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.harsh.urlshortener.domain.UrlInfo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import net.bytebuddy.utility.RandomString;

public class UrlShortenerControllerIntegrationTest {
    public static final String BASE_URL = "http://localhost:8080";
    private WebClient client = WebClient.builder().baseUrl(BASE_URL).build();
    
    @Test
    public void testShortenUrl(){
        ClientResponse response = client.post()
            .uri("/shorten")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(getUrlInfo()))
            .exchange()
            .block();

        assertEquals(HttpStatus.OK, response.statusCode());
        System.out.println("api response "  + response.toString());
    }

    private UrlInfo getUrlInfo(){
        String randomUrlToShorten = "www." + RandomString.make() + ".com";
        UrlInfo urlInfo = new UrlInfo();
        urlInfo.setOriginalUrl(randomUrlToShorten);
        return urlInfo;
    }

}
