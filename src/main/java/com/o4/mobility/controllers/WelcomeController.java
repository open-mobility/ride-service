package com.o4.mobility.controllers;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Hidden
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String health() {
        return "Hello at " + new Date();
    }

}
