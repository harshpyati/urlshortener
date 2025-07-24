package com.harsh.urlshortener.service.impl;

import java.util.Properties;

import com.harsh.urlshortener.domain.UrlInfo;
import com.harsh.urlshortener.repo.UrlShortenerRepository;
import com.harsh.urlshortener.service.IdEncoderService;
import com.harsh.urlshortener.service.IdGenService;
import com.harsh.urlshortener.service.UrlShortenerService;
import com.harsh.urlshortener.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    @Autowired
    private IdGenService idGenService;

    @Autowired
    private IdEncoderService idEncoderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaUrlTemplate;

    private static final String KAFKA_TOPIC = "url_shortener";

    public void validateUrl(UrlInfo urlInfo) {
    }

    @Override
    public UrlInfo shortenUrl(UrlInfo uInfo) {
        validateUrl(uInfo);
        int id = idGenService.generatePseudoRandomId();
        String encodedString = idEncoderService.encodeId(id);
        String shortenUrl = retrieveDomainFromProperties() + encodedString;

        UrlInfo urlInfo = UrlInfo.builder()
                .originalUrl(uInfo.getOriginalUrl())
                .shortenedUrl(shortenUrl)
                .createdTime(System.currentTimeMillis())
                .requiredDuration(uInfo.getRequiredDuration())
                .build();
        return urlShortenerRepository.saveAndFlush(urlInfo);
    }

    @Override
    public UrlInfo redirect(String id) {
        String shortenedUrl = retrieveDomainFromProperties() + id;
        kafkaUrlTemplate.send(KAFKA_TOPIC, shortenedUrl);
        return urlShortenerRepository.getByShortenedUrl(shortenedUrl);
    }

    private String retrieveDomainFromProperties() {
        Properties props = Utils.getApplicationProperties();
        if (props != null) {
            return props.getProperty("domain");
        }
        return null;
    }

}
