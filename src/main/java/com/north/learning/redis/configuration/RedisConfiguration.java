package com.north.learning.redis.configuration;

import com.north.learning.redis.dto.PersonDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> customizedRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, PersonDto> genericToStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, PersonDto> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(RedisSerializer.java());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(PersonDto.class));
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
