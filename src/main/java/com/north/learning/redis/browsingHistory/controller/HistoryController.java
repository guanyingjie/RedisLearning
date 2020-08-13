package com.north.learning.redis.browsingHistory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.north.learning.redis.browsingHistory.model.History;
import com.north.learning.redis.browsingHistory.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
//   默认过期时长，单位：秒
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24 * 30;

    @Autowired
    private HistoryService historyService;

//    新增记录
    @PostMapping("/set")
    public void set(@RequestBody History history) {
        String key = "his_" + history.getUserId();

//        为了保证浏览商品的唯一性,每次添加前,将list中该商品ID去掉,再加入,以保证其浏览的最新的商品在最前面
        historyService.lrem(key, 1, history);

        historyService.lpush(key, history);
//        浏览记录存5条,五条以后切掉
        historyService.ltrim(key, 0,4);
//        设置过期时间
        historyService.expire(key, DEFAULT_EXPIRE);
    }

//    获取
    @GetMapping("/{userId}")
    public List<History> get(@PathVariable("userId") String userId) throws JsonProcessingException {
        return historyService.lrange("his_" + userId, 0, 5);
    }

}
