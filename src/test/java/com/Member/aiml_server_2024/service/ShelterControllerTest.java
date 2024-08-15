package com.Member.aiml_server_2024.service;

import com.Member.aiml_server_2024.controller.ShelterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShelterController.class)
@AutoConfigureMockMvc

public class ShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterService shelterService; // @MockBean으로 모킹된 빈을 주입합니다

    @Test
    public void testGetShelterAddresses() throws Exception {
        List<String> mockAddresses = Arrays.asList("123 Main", "456 Elm");
        when(shelterService.getShelterAddresses()).thenReturn(mockAddresses);

        // API 호출 및 결과 확인
        MvcResult result = mockMvc.perform(get("/api/shelters/addresses"))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("responseContent = " + responseContent);

        assert responseContent.contains("123 Main");
        assert responseContent.contains("456 Elm");
    }
}
