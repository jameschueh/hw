package com.example.hw.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
