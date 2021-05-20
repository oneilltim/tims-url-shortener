package com.oneilltim.urlshortener.controller;
import com.oneilltim.urlshortener.exception.UrlShortenerError;
import com.oneilltim.urlshortener.model.UrlDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value = "/rest/url")
public class UrlShortenerController {

    @Autowired
    private RedisTemplate<String, UrlDto> redisTemplate;

    @Value("${redis.ttl}")
    private long ttl;

    @PostMapping
    public ResponseEntity create(@RequestBody final String url) {
        // Using commons-validator library to validate the input URL.
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (!urlValidator.isValid(url)) {
            // Invalid url return HTTP 400 bad request.
            return ResponseEntity.badRequest().body(new UrlShortenerError("Invalid URL."));
        }

        // If valid URL, generate a hash key using guava's murmur3 hashing algorithm.
        final UrlDto urlDto = UrlDto.create(url);
        log.info("URL id generated = {}", urlDto.getId());
        // Store both hasing key and url object in redis.
        redisTemplate.opsForValue().set(urlDto.getId(), urlDto, ttl, TimeUnit.SECONDS);
        // Return the generated id as a response header.
        return ResponseEntity.noContent().header("id", urlDto.getId()).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUrl(@PathVariable final String id) {
        // Get from redis.
        final UrlDto urlDto = redisTemplate.opsForValue().get(id);
        if (Objects.isNull(urlDto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UrlShortenerError("No such key exists."));
        } else {
            log.info("URL retrieved = {}", urlDto.getUrl());
        }

        return ResponseEntity.ok(urlDto);
    }
}
