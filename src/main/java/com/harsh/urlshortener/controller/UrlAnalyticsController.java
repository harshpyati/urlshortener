package com.harsh.urlshortener.controller;

import com.harsh.urlshortener.domain.UrlStatistics;
import com.harsh.urlshortener.service.impl.UrlAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class UrlAnalyticsController {

    @Autowired
    UrlAnalyticsService urlAnalyticsService;

    @GetMapping
    public List<UrlStatistics> getMostVisitedSites() {
        return urlAnalyticsService.getMostUsedUrls();
    }
}
