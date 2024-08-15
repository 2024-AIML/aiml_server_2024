package com.Member.aiml_server_2024.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShelterControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetShelterAddresses() {
        String url = "http://localhost:" + port + "/api/shelters/addresses";

        // 여기에 실제 Firebase에 대한 호출이 필요합니다.

        // 실제 요청을 보내고 응답을 검증합니다.
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).contains("123 Main", "456 Elm");
    }
}
