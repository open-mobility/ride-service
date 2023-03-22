package com.o4.mobility.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String health() {
        return "Hello at " + new Date();
    }

}
