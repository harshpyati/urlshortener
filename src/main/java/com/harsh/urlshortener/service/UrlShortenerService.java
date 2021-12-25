package com.harsh.urlshortener.service;

import com.harsh.urlshortener.domain.UrlInfo;

import org.springframework.http.ResponseEntity;

public interface UrlShortenerService {
    UrlInfo shortenUrl(String url);

    UrlInfo redirect(String id);
}
