package ru.netology.Cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello, user!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, admin!";
    }
}
