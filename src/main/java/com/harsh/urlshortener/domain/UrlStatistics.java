package com.harsh.urlshortener.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "url_statistics")
@Data @AllArgsConstructor @NoArgsConstructor
public class UrlStatistics {
    @Id
    String url;
    Integer count;
}
