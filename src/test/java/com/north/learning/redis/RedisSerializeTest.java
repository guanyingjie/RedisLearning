package com.north.learning.redis;

import com.north.learning.redis.dto.PersonDto;
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

    @Autowired
    private RedisTemplate<String, Object> customizedRedisTemplate;

    @Autowired
    private RedisTemplate<String, PersonDto> genericToStringRedisTemplate;

    @Test
    void should_set_key_value_success_when_use_default_redis_template() {
        redisTemplate.opsForValue().set("what we learning", "redis");
        String learning = (String) redisTemplate.opsForValue().get("what we learning");
        assertEquals("redis", learning);
    }

    @Test
    void should_save_object_as_value_success_when_use_jdk_serializer() {
        redisTemplate.opsForValue().set("object", new PersonDto("redis", 20));
        PersonDto person = (PersonDto) redisTemplate.opsForValue().get("object");
        assertEquals("redis", person.getName());
    }

    @Test
    void should_save_object_success_when_use_generic_to_string_serializer() {
        genericToStringRedisTemplate.opsForValue().set("generic", new PersonDto("generic", 18));
        PersonDto generic = (PersonDto) genericToStringRedisTemplate.opsForValue().get("generic");
        assertEquals(99, (int) generic.getAge());
    }

    @Test
    void should_set_key_value_when_use_customized_redis_template() {
        System.out.println(customizedRedisTemplate.getKeySerializer());
        customizedRedisTemplate.opsForValue().set("what we are learning", "redis");
        String learning = (String) customizedRedisTemplate.opsForValue().get("what we are learning");
        assertEquals("redis", learning);
    }
}
