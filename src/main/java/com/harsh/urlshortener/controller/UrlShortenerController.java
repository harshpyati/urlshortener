package com.harsh.urlshortener.controller;

import java.net.URI;
import java.net.URISyntaxException;
import com.harsh.urlshortener.domain.UrlInfo;
import com.harsh.urlshortener.service.UrlShortenerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shorten")
@RestController
public class UrlShortenerController {
    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping
    public UrlInfo shortenUrl(@RequestParam("url") String url) {
        return urlShortenerService.shortenUrl(url);
    }

    @GetMapping(value = "/redirect")
    public ResponseEntity<Object> redirect(@RequestParam("id") String id) throws URISyntaxException{
        UrlInfo urlInfo = urlShortenerService.redirect(id);
        String formattedUrl = urlInfo.getOriginalUrl();
        if(!(formattedUrl.contains("http") || formattedUrl.contains("htttps"))){
            formattedUrl = "https://" + formattedUrl;
        }
        URI uri = new URI(formattedUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
