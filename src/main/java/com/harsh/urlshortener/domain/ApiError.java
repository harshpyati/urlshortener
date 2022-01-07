package com.harsh.urlshortener.domain;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    String message;
    Integer code;
}
