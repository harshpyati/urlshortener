package com.harsh.urlshortener.service.impl;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;
import com.harsh.urlshortener.domain.UrlInfo;
import com.harsh.urlshortener.repo.UrlShortenerRepository;
import com.harsh.urlshortener.service.UrlShortenerService;
import com.harsh.urlshortener.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;

public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    public void validateUrl(UrlInfo urlInfo) {
    }

    public boolean checkIfShortenedUrlExists(String url) {
        return false;
    }

    @Override
    public UrlInfo shortenUrl(UrlInfo uInfo) {
        validateUrl(uInfo);
        UrlInfo urlInfo = urlShortenerRepository.getByOriginalUrl(uInfo.getOriginalUrl());
        if (urlInfo == null) {
            String shortenUrl = retrieveDomainFromProperties() + UUID.randomUUID().toString();
            System.out.println(shortenUrl);
            urlInfo = new UrlInfo();
            urlInfo.setOriginalUrl(uInfo.getOriginalUrl());
            urlInfo.setShortenedUrl(shortenUrl);
            urlInfo.setCreatedTime(System.currentTimeMillis());
            urlInfo.setRequiredDuration(uInfo.getRequiredDuration());
            urlShortenerRepository.save(urlInfo);
        }
        return urlInfo;
    }

    @Override
    public UrlInfo redirect(String id) {
        String shortenedUrl = retrieveDomainFromProperties() + id;
        UrlInfo urlInfo = urlShortenerRepository.getByShortenedUrl(shortenedUrl);
        return urlInfo;
    }

    private String retrieveDomainFromProperties(){
        Properties props = Utils.getApplicationProperties();
        if(props != null){
            return props.getProperty("domain");
        } return null;
    }

}
