package com.north.learning.redis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedisSerializeTest {
    
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void should_set_key_value_success_when_use_default_redis_template() {
        redisTemplate.opsForValue().set("what we learning", "redis");
        String learning = (String) redisTemplate.opsForValue().get("what we learning");
        assertEquals("redis", learning);
    }
}
