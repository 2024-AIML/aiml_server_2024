package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.config.auth.JwtTokenProvider;
import com.Member.aiml_server_2024.userInfo.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class MemberApiController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final UserInfoService userInfoService;

    public MemberApiController(MemberService memberService, JwtTokenProvider jwtTokenProvider, MemberRepository memberRepository, UserInfoService userInfoService) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberRepository = memberRepository;
        this.userInfoService = userInfoService;
    }

    @PostMapping("/api/member")
    public void save(@RequestBody Member.SaveRequest member) throws ExecutionException, InterruptedException {
        memberService.save(member);

        Member mem = member.toEntity();
        userInfoService.saveUserInfo(mem);

    }

//    @GetMapping("/api/member/info")
//    public ResponseEntity<Member> getMemberInfo(Authentication authentication) {
//        String id = authentication.getName();
//        Member member = memberService.getUserById(id);
//        return ResponseEntity.ok(member);
//    }

    @GetMapping("/api/member/info")
    public ResponseEntity<Member.SafeInfo> getMemberInfo(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        String memberId = jwtTokenProvider.getMemberIdFromToken(jwt);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Member.SafeInfo safeInfo =
                new Member.SafeInfo(member.getId(), member.getName(), member.getPhoneNum());

        return ResponseEntity.ok(safeInfo);
    }
}
