package com.harsh.urlshortener.service.impl;

import java.util.UUID;
import com.harsh.urlshortener.domain.UrlInfo;
import com.harsh.urlshortener.repo.UrlShortenerRepository;
import com.harsh.urlshortener.service.UrlShortenerService;

import org.springframework.beans.factory.annotation.Autowired;

public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    public void validateUrl(String url) {
    }

    public boolean checkIfShortenedUrlExists(String url) {
        return false;
    }

    @Override
    public UrlInfo shortenUrl(String url) {
        validateUrl(url);
        UrlInfo urlInfo = urlShortenerRepository.getByOriginalUrl(url);
        if (urlInfo == null) {
            String shortenUrl = "https://localhost:8080/" + UUID.randomUUID().toString();
            System.out.println(shortenUrl);
            urlInfo = new UrlInfo();
            urlInfo.setOriginalUrl(url);
            urlInfo.setShortenedUrl(shortenUrl);
            urlShortenerRepository.save(urlInfo);
        }
        return urlInfo;
    }

    @Override
    public UrlInfo redirect(String id) {
        String shortenedUrl = "https://localhost:8080/" + id;
        UrlInfo urlInfo = urlShortenerRepository.getByShortenedUrl(shortenedUrl);
        return urlInfo;
    }

}
