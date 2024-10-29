package com.Member.aiml_server_2024.controller;

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
    public void saveUserInfo(@RequestBody UserInfo userInfo) throws ExecutionException, InterruptedException {
        userInfoService.saveUserInfo(userInfo);
    }

    @GetMapping("/get")
    public UserInfo getUserInfo(@RequestParam String id) throws ExecutionException, InterruptedException {
        UserInfo us = userInfoService.getUserInfo(id);
        return us;
    }
}
