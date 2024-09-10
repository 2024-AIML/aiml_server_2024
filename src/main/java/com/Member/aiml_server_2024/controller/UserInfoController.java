package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.userInfo.UserInfo;
import com.Member.aiml_server_2024.userInfo.UserInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("UserInfo")
public class UserInfoController {

    private final UserInfoServiceImpl userInfoService;

    @GetMapping("get")
    public UserInfo getUserInfo(@RequestParam String name) throws ExecutionException, InterruptedException {
        UserInfo us = userInfoService.getUserInfo(name);
        return us;
    }
}
