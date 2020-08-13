package com.north.learning.redis.rank.service;

import com.north.learning.redis.rank.model.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankService {
    @Autowired
    @Qualifier(value = "genericJacksonToJson")
    private RedisTemplate<String, Object> redisTemplate;

    private static String SCORE_RANK = "score_rank";

    public void add(String value, double score) {
        redisTemplate.opsForZSet().add(SCORE_RANK, value, score);
    }

    public Rank updateScore(String userId, double score) {
        add(userId, score);
        return getRankByUser(userId);
    }

    public Rank getRankByUser(String userId) {
        Long rank = redisTemplate.opsForZSet().rank(SCORE_RANK, userId);
        if (rank == null) {
            return Rank.builder().userId(userId).rank(-1L).score(0d).build();
        }
        double score = redisTemplate.opsForZSet().score(SCORE_RANK, userId);
        return Rank.builder().userId(userId).rank(rank + 1).score(score).build();
    }

    public List<Rank> getRankRange(int start, int end) {
        Set<ZSetOperations.TypedTuple<Object>> resultSet = redisTemplate.opsForZSet()
                .rangeWithScores(SCORE_RANK, start - 1, end - 1);
        long rank = start;
        List<Rank> rankList = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> sub : resultSet) {
            rankList.add(Rank.builder().userId(sub.getValue().toString())
                    .score(sub.getScore())
                    .rank(rank++)
                    .build());
        }
        return rankList;
    }
}
