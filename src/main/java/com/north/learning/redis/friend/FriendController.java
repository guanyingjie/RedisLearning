package com.north.learning.redis.friend;

import com.north.learning.redis.friend.Servivce.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;

    //添加好友
    @PostMapping("/add")
    public Long addFriend(@RequestParam("userId") String userId,
                          @RequestParam("friendId") String friendId) {
        Long add = friendService.add(userId,friendId);
        return add;

    }

    //删除好友
    @DeleteMapping("/delete")
    public Long deleteFriend(@RequestParam("userId") String userId,
                             @RequestParam("friendId") String friendId) {
        Long remove = friendService.remove(userId,friendId);
        return remove;
    }

    //
//所有好友
    @GetMapping("/{userId}")
    public Set<Object> getFriendByUser(@PathVariable("userId") String userId) {
        Set<Object>member = friendService.members(userId);
        return member;
    }

    //共同好友
    @GetMapping("/intersectFriend")
    public Set intersectFriend(@RequestParam("userAId") String userAId,
                               @RequestParam("userBId") String userBId) {
        Set intersect = friendService.intersect(userAId,userBId);
        return intersect;
    }

    //独有好友
    @GetMapping("/differenceFriend")
    public Set differenceFriend(@RequestParam("userAId") String userAId,
                                @RequestParam("userBId") String userBId) {
        Set difference = friendService.difference(userAId,userBId);
        return difference;

    }

}
