package com.north.learning.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @GetMapping(value = "/hello")
    public String greeting(@RequestParam(value = "name") String name) {
        System.out.println("use new person name");
        return "nice to meet you, " + name;
    }
}
