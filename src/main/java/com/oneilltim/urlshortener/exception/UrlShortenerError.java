package com.oneilltim.urlshortener.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlShortenerError {
        private final String message;
}
