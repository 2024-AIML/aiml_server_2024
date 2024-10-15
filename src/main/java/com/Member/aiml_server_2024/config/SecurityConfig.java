package com.Member.aiml_server_2024.config;

import com.Member.aiml_server_2024.config.auth.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/location/**", "/UserInfo/get", "/post/**"));
        // 예외처리 하고 싶은 url
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/infra_info").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                                .loginPage("/navigator")    // 사용자 정의 로그인 페이지
                                .loginProcessingUrl("/api/login")   // 로그인 처리 URL
                                .usernameParameter("id")
                                .passwordParameter("password")
//                        .defaultSuccessUrl("/infra_info", true) // 로그인 성공 시 이동할 URL
                                .successHandler((request, response, authentication) -> {
                                    String userId = authentication.getName();

                                    // 로그인 성공 시 JWT 토큰 생성
                                    String token = jwtTokenProvider.createdToken(userId);

                                    response.setStatus(HttpServletResponse.SC_OK);
                                    response.setContentType("application/json");
                                    Map<String, String> responseData = new HashMap<>();
                                    responseData.put("token", token);
                                    responseData.put("message", "Login successful");
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    response.getWriter().write(objectMapper.writeValueAsString(responseData));
                                    response.getWriter().flush();
                                })
                                .failureHandler((request, response, exception) -> {
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.setContentType("application/json");
                                    response.getWriter().write("{\"message\": \"Login failed\"}");
                                    response.getWriter().flush();
                                })
                                .permitAll()    // 로그인 페이지 접근 허용
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")   // 로그이웃 처리 URL
                        .logoutSuccessUrl("/navigator")
                )
                .csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
