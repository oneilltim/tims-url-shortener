package com.oneilltim.urlshortener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TimsURLShortenerApp {

    public static void main(String[] args) {
        SpringApplication.run(TimsURLShortenerApp.class, args);
        log.info("Springboot and url-shortener application started successfully.");
    }
}
