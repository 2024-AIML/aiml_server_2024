package com.Member.aiml_server_2024.userInfo;

import java.util.concurrent.ExecutionException;

public interface UserInfoService {

    Member getUserInfo(String name) throws ExecutionException, InterruptedException;

    void saveUserInfo(Member member) throws ExecutionException, InterruptedException;
}
