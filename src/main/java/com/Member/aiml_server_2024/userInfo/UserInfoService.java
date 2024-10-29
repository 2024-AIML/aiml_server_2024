package com.Member.aiml_server_2024.userInfo;

import java.util.concurrent.ExecutionException;

public interface UserInfoService {

    UserInfo getUserInfo(String name) throws ExecutionException, InterruptedException;

    void saveUserInfo(UserInfo userInfo) throws ExecutionException, InterruptedException;
}
