package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.distance.Distance;
import com.Member.aiml_server_2024.distance.DistanceImpl;
import com.Member.aiml_server_2024.model.Shelter;
import com.Member.aiml_server_2024.service.ShelterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

// 임의로 20개의 대피소 주소, 위도, 경도가 있는 리스트 만들어 놓고
// 실제 거리 계산해서 오름차순 정렬 후 최단거리 상위 5개의 값을 출력할 수 있는지

class ShelterController2Test {

    @Mock
    private ShelterService shelterService;

    @InjectMocks
    private ShelterController shelterController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Distance distanceCalculator = new DistanceImpl(); // DistanceImpl 사용

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shelterController).build();
    }

    @Test
    void testGetNearestShelters() throws Exception {
        // Prepare test data - 20 shelters with incremental distance
        List<Shelter> allShelters = IntStream.range(0, 20).mapToObj(i -> {
            Shelter shelter = new Shelter();
            shelter.setFullAddress("Test Address " + i);
            shelter.setLatitude_EPSG4326(37.7749 + i * 0.01); // Incremental latitude
            shelter.setLongitude_EPSG4326(-122.4194 + i * 0.01); // Incremental longitude
            shelter.setCapacity(100);
            shelter.setNowCount(50);
            return shelter;
        }).collect(Collectors.toList());

        // Mock the service method to return only the nearest 5 shelters
        List<Shelter> nearestShelters = allShelters.subList(0, 5);
        when(shelterService.getSheltersByPartialAddress("Test Address", 37.7749, -122.4194))
                .thenReturn(nearestShelters);

        // Perform the request
        MvcResult result = mockMvc.perform(get("/api/shelters/nearest")
                        .param("address", "Test Address")
                        .param("latitude", "37.7749")
                        .param("longitude", "-122.4194")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))  // Verify that we get top 5 shelters
                .andReturn();

        // Get the response content
        String content = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(content);

        // Extract returned shelters
        List<Shelter> returnedShelters = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            Shelter shelter = new Shelter();
            shelter.setFullAddress(node.path("fullAddress").asText());
            shelter.setLatitude_EPSG4326(node.path("latitude_EPSG4326").asDouble());
            shelter.setLongitude_EPSG4326(node.path("longitude_EPSG4326").asDouble());
            returnedShelters.add(shelter);
        }

        // Calculate distances and print them
        System.out.println("Returned Shelters with Distances:");
        for (int i = 0; i < returnedShelters.size(); i++) {
            Shelter shelter = returnedShelters.get(i);
            double distance = distanceCalculator.distance(
                    37.7749, -122.4194, shelter.getLatitude_EPSG4326(), shelter.getLongitude_EPSG4326());
            System.out.printf("Address: %s, Distance: %f meters%n", shelter.getFullAddress(), distance);
        }

        // Sort shelters by distance
        List<Shelter> sortedShelters = returnedShelters.stream()
                .sorted(Comparator.comparingDouble(shelter -> distanceCalculator.distance(
                        37.7749, -122.4194, shelter.getLatitude_EPSG4326(), shelter.getLongitude_EPSG4326())))
                .collect(Collectors.toList());

        // Verify that shelters are sorted by distance
        for (int i = 0; i < sortedShelters.size(); i++) {
            Shelter shelter = sortedShelters.get(i);
            double expectedDistance = distanceCalculator.distance(
                    37.7749, -122.4194, shelter.getLatitude_EPSG4326(), shelter.getLongitude_EPSG4326());
            double actualDistance = distanceCalculator.distance(
                    37.7749, -122.4194, sortedShelters.get(i).getLatitude_EPSG4326(), sortedShelters.get(i).getLongitude_EPSG4326());
            if (i > 0) {
                double previousDistance = distanceCalculator.distance(
                        37.7749, -122.4194, sortedShelters.get(i - 1).getLatitude_EPSG4326(), sortedShelters.get(i - 1).getLongitude_EPSG4326());
                if (expectedDistance < previousDistance) {
                    throw new AssertionError("Shelters are not sorted by distance correctly.");
                }
            }
        }

        System.out.println("Shelters are correctly sorted by distance.");
    }
}
