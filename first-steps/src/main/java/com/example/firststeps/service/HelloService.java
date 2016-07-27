package com.example.firststeps.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

    @Value("${hello.name}")
    private String name;

    @RequestMapping("/")
    String home() {
        return "Hello, " + name + "!";
    }
}
