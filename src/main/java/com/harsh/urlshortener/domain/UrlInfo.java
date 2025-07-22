package com.harsh.urlshortener.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Data
@Entity(name = "urls")
@Builder @AllArgsConstructor @NoArgsConstructor
public class UrlInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String originalUrl;

    String shortenedUrl;

    Long createdTime;

    Long requiredDuration = 60 * 60 * 60L;
}
