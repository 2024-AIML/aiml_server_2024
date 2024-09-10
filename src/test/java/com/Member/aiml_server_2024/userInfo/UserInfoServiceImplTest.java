package com.Member.aiml_server_2024.userInfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class UserInfoServiceImplTest {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Test
    public void getUserInfo() throws ExecutionException, InterruptedException {
        UserInfo us = userInfoService.getUserInfo("가나다");
        System.out.println(us.getName());
        System.out.println(us.getPhoneNum());
    }
}