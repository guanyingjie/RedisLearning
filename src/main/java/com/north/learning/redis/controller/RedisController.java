package com.north.learning.redis.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RedisController {

    private final RedisTemplate redisTemplate;


    @GetMapping(value = "/hello")
    public String greeting(@RequestParam(value = "name") String name) {
        String cacheName = (String) redisTemplate.opsForValue().get(name);
        if (cacheName != null) {
            return "hello again, " + cacheName;
        }
        redisTemplate.opsForValue().set(name, name);
        System.out.println("use new person name");
        return "nice to meet you, " + name;
    }
}
