package com.north.learning.redis.browsingHistory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {

    private String userId;
    private String itemName;
}
