package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.config.auth.JwtTokenProvider;
import com.Member.aiml_server_2024.userInfo.Member;
import com.Member.aiml_server_2024.userInfo.MemberRepository;
import com.Member.aiml_server_2024.userInfo.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberApiController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public MemberApiController(MemberService memberService, JwtTokenProvider jwtTokenProvider, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/api/member")
    public void save(@RequestBody Member.SaveRequest member) {
        memberService.save(member);
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
