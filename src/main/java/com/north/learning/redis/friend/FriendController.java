package com.north.learning.redis.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;

    @PostMapping
    public Long addFriend(@RequestParam("userId") String userId,
                          @RequestParam("friendId") String friendId) {
        return 1L;
    }

    @DeleteMapping
    public Long deleteFriend(@RequestParam("userId") String userId,
                             @RequestParam("friendId") String friendId) {
        return 1L;
    }

    @GetMapping("/{userId}")
    public Set<Object> getFriendByUser(@PathVariable("userId") String userId) {
        return null;
    }

    @GetMapping("/intersectFriend")
    public Set intersectFriend(@RequestParam("userAId") String userAId,
                               @RequestParam("userBId") String userBId) {
        return null;
    }

    @GetMapping("/differenceFriend")
    public Set differenceFriend(@RequestParam("userAId") String userAId,
                                @RequestParam("userBId") String userBId) {
        return null;

    }

}
