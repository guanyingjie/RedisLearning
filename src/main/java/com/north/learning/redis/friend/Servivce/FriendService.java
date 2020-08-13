package com.north.learning.redis.friend.Servivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FriendService {

    @Autowired
    @Qualifier(value = "genericJacksonToJson")
    private RedisTemplate<String,Object> redisTemplate;

    public Long add(String userId, String friendId){
        Long add = redisTemplate.opsForSet().add(userId,friendId);
        return add;
    }

    public Long remove(String userId, String friendId){
        Long remove = redisTemplate.opsForSet().remove(userId,friendId);
        return remove;
    }
    public Set<Object> members(String userId){
        Set<Object> set = redisTemplate.opsForSet().members(userId);
        return set;
    }
    public Set intersect(String userAid,String userBid){
        Set intersect = redisTemplate.opsForSet().intersect(userAid,userBid);
        return intersect;
    }
    public Set difference(String userAid,String userBid){
        Set difference = redisTemplate.opsForSet().difference(userAid,userBid);
        return difference;
    }

}
