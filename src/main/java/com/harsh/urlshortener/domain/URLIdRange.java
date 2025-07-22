package com.harsh.urlshortener.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "url_id_range")
@Data
public class URLIdRange {
    @Id
    Integer id;
    Integer startRange;
    Integer endRange;
    Integer current;
}
