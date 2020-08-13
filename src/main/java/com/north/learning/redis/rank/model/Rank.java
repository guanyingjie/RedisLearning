package com.north.learning.redis.rank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rank {
    String userId;
//    排名
    Long rank;
//    积分
    Double score;
}
