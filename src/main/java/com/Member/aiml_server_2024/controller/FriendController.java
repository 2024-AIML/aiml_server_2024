package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.model.Friend;
import com.Member.aiml_server_2024.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/add")
    public String addFriend(@RequestParam String userID, @RequestBody Friend friend) {
        try {
            friendService.addFriend(userID, friend);
            return "친구가 성공적으로 추가되었습니다 !";
        } catch (ExecutionException | InterruptedException e) {
            return "친구가 추가되지 않았습니다. " + e.getMessage();
        }
    }

    @DeleteMapping("/delete")
    public String deleteFriend(@RequestParam String uid, @RequestParam String friendId) {
        try {
            friendService.deleteFriend(uid, friendId);
            return "친구가 성공적으로 삭제되었습니다 !";
        } catch (ExecutionException | InterruptedException e) {
            return "친구를 삭제하는데 오류가 생겼습니다.  " + e.getMessage();
        }
    }
}
