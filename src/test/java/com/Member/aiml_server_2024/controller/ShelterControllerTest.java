package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.model.Shelter;
import com.Member.aiml_server_2024.service.ShelterService;
import com.Member.aiml_server_2024.model.MakeShelterList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ShelterController.class)
@ActiveProfiles("test")
public class ShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterService shelterService;

    @MockBean
    private MakeShelterList makeShelterList;

    @BeforeEach
    public void setUp() {
        // 여기에서 필요한 초기화 작업을 할 수 있습니다.
    }

    @Test
    public void testGetShelterAddresses() throws Exception {
        // Mock the behavior of shelterService
        when(shelterService.getShelterAddresses()).thenReturn(Collections.singletonList("123 Main St"));

        // Perform the GET request and verify the result
        mockMvc.perform(get("/api/shelters/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("123 Main St")));

        // 테스트 통과 메시지 출력
        System.out.println("testGetShelterAddresses passed successfully.");
    }

    @Test
    public void testGetShelterCoordinates() throws Exception {
        // Create a mock Shelter object
        Shelter shelter = new Shelter();
        shelter.setInfraName("Test Shelter");
        shelter.setLocation("Test Location");
        shelter.setFullAddress("123 Main St");

        // Mock the behavior of makeShelterList
        when(makeShelterList.getSheltersByPartialAddress("123 Main St"))
                .thenReturn(Collections.singletonList(shelter));

        // Perform the GET request and verify the result
        mockMvc.perform(get("/api/shelters/coordinates")
                        .param("address", "123 Main St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].infraName", is("Test Shelter")));

        // 테스트 통과 메시지 출력
        System.out.println("testGetShelterCoordinates passed successfully.");
    }
}
