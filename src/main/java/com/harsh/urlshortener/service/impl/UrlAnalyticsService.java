package com.harsh.urlshortener.service.impl;

import com.harsh.urlshortener.domain.UrlStatistics;
import com.harsh.urlshortener.repo.UrlStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlAnalyticsService {

    @Autowired
    UrlStatisticsRepository urlStatsRepo;

    @KafkaListener(topics = "url_shortener")
    public void consumeMessage(String message) {
        Optional<UrlStatistics> urlStatistics = urlStatsRepo.findById(message);
        if (urlStatistics.isEmpty()) {
            urlStatsRepo.save(new UrlStatistics(message, 1));
        } else {
            UrlStatistics existingStats = urlStatistics.get();
            existingStats.setCount(existingStats.getCount() + 1);
            urlStatsRepo.save(existingStats);
        }
    }

    public List<UrlStatistics> getMostUsedUrls() {
        return urlStatsRepo.getMostUsedUrls();
    }
}
