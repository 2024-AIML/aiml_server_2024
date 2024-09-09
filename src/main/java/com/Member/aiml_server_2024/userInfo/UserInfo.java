package com.Member.aiml_server_2024.userInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserInfo {

    private String name;
    private String phoneNum;

    public UserInfo(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }
}
