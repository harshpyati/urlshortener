package com.harsh.urlshortener.controller;

import com.harsh.urlshortener.domain.UrlInfo;
import com.harsh.urlshortener.service.UrlShortenerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shorten")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UrlShortenerController {
    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping
    public UrlInfo shortenUrl(@RequestBody UrlInfo urlInfo) {
        return urlShortenerService.shortenUrl(urlInfo);
    }
}
