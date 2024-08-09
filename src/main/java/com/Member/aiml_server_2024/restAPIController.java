package com.Member.aiml_server_2024;

import com.Member.aiml_server_2024.navigation.navigationScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class restAPIController {

    private final navigationScheme navigationScheme;

    @GetMapping("/navi")
    public Map<String, Object> navi(){

        return navigationScheme.GetWalkRoute();
    }
}
