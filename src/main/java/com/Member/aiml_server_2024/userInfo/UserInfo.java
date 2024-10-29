package com.Member.aiml_server_2024.userInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserInfo {

    private String name;
    private String password;
    private String phoneNum;
    private String id;

    public UserInfo(String id) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
    }
}
