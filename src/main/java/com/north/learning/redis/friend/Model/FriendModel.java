package com.north.learning.redis.friend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendModel {
    private String userId;

    private String friendId;
}
