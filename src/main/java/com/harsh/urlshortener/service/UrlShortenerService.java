package com.harsh.urlshortener.service;

import com.harsh.urlshortener.domain.UrlInfo;

public interface UrlShortenerService {
    UrlInfo shortenUrl(String url);

    UrlInfo redirect(String id);
}
