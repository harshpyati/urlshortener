package com.harsh.urlshortener.repo;

import com.harsh.urlshortener.domain.UrlInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepository extends JpaRepository<UrlInfo, Integer>{
    UrlInfo getByOriginalUrl(String originalUrl);

    UrlInfo getByShortenedUrl(String shortenedUrl);
}
