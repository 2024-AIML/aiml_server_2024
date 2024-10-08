package com.Member.aiml_server_2024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/infra_info1")
    public String main() {
        return "infra_info";
    }

    @GetMapping("/navigator2")
    public String login() {
        return "navigator";
    }

    @GetMapping("/register")
    public String register() {
        return "register";  // 회원가입 페이지
    }
}
