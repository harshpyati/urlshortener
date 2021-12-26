package com.harsh.urlshortener.service;

import com.harsh.urlshortener.domain.UrlInfo;

public interface UrlShortenerService {
    UrlInfo shortenUrl(UrlInfo urlnInfo);

    UrlInfo redirect(String id);
}
