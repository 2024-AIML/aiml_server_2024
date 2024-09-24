package com.Member.aiml_server_2024.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class FriendRequest {
    private String userId;    // 요청을 보낸 사용자 ID
    private String friendId;   // 추가하려는 친구 ID
    private String friendName; // 친구 이름
    private String friendEmail; // 친구 이메일
    private String friendPhone; // 친구 전화번호

    public FriendRequest(String userId, String friendId, String friendName, String friendEmail, String friendPhone) {
        this.userId = userId;
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendEmail = friendEmail;
        this.friendPhone = friendPhone;
    }

}
