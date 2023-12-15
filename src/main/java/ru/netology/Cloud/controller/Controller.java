package ru.netology.Cloud.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

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

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

//    @PostMapping("/login")
//    public String login(@RequestHeader("auth-token") String token) {
//        System.out.println(token);
//        return token;
//    }
    @PostMapping("/login")
    public String login() {
//        System.out.println(headers);
//        System.out.println(body);
        return "{\"auth-token\"=: \"zldigtbujplbzfgudibpuigbpofiuzgb\"}";
    }
}
