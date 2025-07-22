package com.harsh.urlshortener.controller;

import java.net.URI;
import java.net.URISyntaxException;

import com.harsh.urlshortener.domain.ApiError;
import com.harsh.urlshortener.domain.UrlInfo;
import com.harsh.urlshortener.service.UrlShortenerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class UrlRedirectController {
    @Autowired
    UrlShortenerService urlShortenerService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> redirect(@PathVariable("id") String id) throws URISyntaxException {
        UrlInfo urlInfo = urlShortenerService.redirect(id);
        String formattedUrl = urlInfo.getOriginalUrl();
        if (!(formattedUrl.contains("http") || formattedUrl.contains("htttps"))) {
            formattedUrl = "https://" + formattedUrl;
        }
        URI uri = new URI(formattedUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/alias")
    public UrlInfo shortenUrl(@RequestBody UrlInfo urlInfo) {
        return urlShortenerService.shortenUrl(urlInfo);
    }

}
