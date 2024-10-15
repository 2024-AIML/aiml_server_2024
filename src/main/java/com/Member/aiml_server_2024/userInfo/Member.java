package com.Member.aiml_server_2024.userInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor  // JPA를 이용하기 위해 기본 생성자를 Lombok으로 선언
@Getter
@Entity(name = "Member")    // 데이터베이스 테이블과 매핑되는 클래스임을 선언
public class Member {
    @Id // id 칼럼을 MEMBER 테이블의 기본키로 설정
    private String id;
    private String password;
    private String name;
    private String phoneNum;
//    private MemberAuthority authority; // USER인지 ADMIN인지 구분

    @Builder
    public Member(String id, String password, String name, String phoneNum) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public static class SafeInfo {


        private String id;
        private String name;
        private String phoneNum;

        public SafeInfo(String id, String name, String phoneNum) {
            this.id = id;
            this.name = name;
            this.phoneNum = phoneNum;
        }
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SaveRequest {
        private String id;
        private String password;
        private String name;
        private String phoneNum;

        @Transient
        public Member toEntity() {
            return Member.builder()
                    .id(this.id)
                    .password(this.password)
                    .name(this.name)
                    .phoneNum(this.phoneNum)
                    .build();
        }

    }

}
