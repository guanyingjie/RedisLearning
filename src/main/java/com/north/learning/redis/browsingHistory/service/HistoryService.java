package com.north.learning.redis.browsingHistory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.north.learning.redis.browsingHistory.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class HistoryService {
    @Autowired
    @Qualifier(value = "genericJacksonToJson")
    private RedisTemplate<String, Object> redisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    //    删除指定key
    public void lrem(String key, int count, History value) {
        redisTemplate.opsForList().remove(key, count, value);

    }

    public void lpush(String key, History value) {
        redisTemplate.opsForList().leftPush(key, value);

    }

    //    裁剪
    public void ltrim(String key, int start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    //    设置过期时间
    public void expire(String key, long seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public List<History> lrange(String key, int start, int end) {
        return (List) redisTemplate.opsForList().range(key, start, end);
    }

}
