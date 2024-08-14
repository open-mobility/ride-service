package com.o4.mobility.controllers;


import com.o4.mobility.dtos.HealthStatus;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Welcome controller helps in check health status of the application
 *
 * <p>It also helps in testing Web-Layer in isolation, where one need to verify
 * 1. Redirection
 * 2. Header value
 * 3. Cookie value
 * </p>
 *
 * @author M. Mazhar Hassan
 * @see HealthStatus
 * @since 1.0
 */

@Hidden
@Slf4j
@RestController
public class WelcomeController {

    @GetMapping("/")
    public HealthStatus health() {
        return new HealthStatus();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Sir how are you? " + new Date();
    }

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectToHello() {

        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/hello")
                .build();
    }


    @GetMapping("/web-layer")
    public ResponseEntity<HealthStatus> get(HttpServletResponse response) {
        log.info("Testing web Layer");

        // Add custom header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "I am the Header");

        // Create and add the cookie
        Cookie cookie = new Cookie("Custom-Cookie", "I am the Cookie");
        cookie.setHttpOnly(true); // Secure cookie
        cookie.setMaxAge(3600); // Expiration time in seconds
        response.addCookie(cookie);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new HealthStatus());
    }

}
