package com.north.learning.redis.rank.controller;

import com.north.learning.redis.rank.model.Rank;
import com.north.learning.redis.rank.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankService rankService;

    @PostMapping
    public void add(@RequestParam("userId") String userId,
                    @RequestParam("score") double score) {
        rankService.add(userId, score);
    }

//    更新
    @PutMapping("/{userId}")
    public Rank updateScore(@PathVariable String userId,
                            @RequestParam("score") double score) {
        return rankService.updateScore(userId, score);
    }

//    获取指定用户排名
    @GetMapping("/{userId}")
    public Rank getRankByUser(@PathVariable String userId) {

        return rankService.getRankByUser(userId);
    }

//    获取topN,排名
    @GetMapping("/topN")
    public List<Rank> getTopN(@RequestParam("number") int n) {

        return rankService.getRankRange(1, n);
    }

}
