package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.userInfo.Member;
import com.Member.aiml_server_2024.userInfo.UserInfo;
import com.Member.aiml_server_2024.userInfo.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/UserInfo")
public class UserInfoController {

    private final UserInfoServiceImpl userInfoService;

    @PostMapping("/save")
    public void saveUserInfo(@RequestBody Member userInfo) throws ExecutionException, InterruptedException {
        userInfoService.saveUserInfo(userInfo);
    }

    @GetMapping("/get")
    public Member getUserInfo(@RequestParam String id) throws ExecutionException, InterruptedException {
        Member us = userInfoService.getUserInfo(id);
        return us;
    }
}
