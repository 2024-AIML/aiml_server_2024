//package com.Member.aiml_server_2024.controller;
//
//import com.Member.aiml_server_2024.model.Shelter;
//import com.Member.aiml_server_2024.service.ShelterService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.mockito.Mockito.when;
//
//class ShelterController2Test {
//
//    @Mock
//    private ShelterService shelterService;
//
//    @InjectMocks
//    private ShelterController shelterController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(shelterController).build();
//    }
//
//    @Test
//    void testGetNearestShelters() throws Exception {
//        // Prepare test data
//        List<Shelter> shelters = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Shelter shelter = new Shelter();
//            shelter.setFullAddress("Test Address " + i);
//            shelter.setLatitude_EPSG4326(37.7749 + i * 0.01);
//            shelter.setLongitude_EPSG4326(-122.4194 + i * 0.01);
//            shelter.setCapacity(100);
//            shelter.setNowCount(50);
//            shelters.add(shelter);
//        }
//
//        // Mock Service response
//        when(shelterService.getSheltersByPartialAddress("Test Address", 37.7749, -122.4194))
//                .thenReturn(shelters);
//
//        // Perform the request and verify the result
//        mockMvc.perform(get("/api/shelters/nearest")
//                        .param("address", "Test Address")
//                        .param("latitude", "37.7749")
//                        .param("longitude", "-122.4194")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(5)))  // Assuming you want to test for top 5 shelters
//                .andExpect(jsonPath("$[0].fullAddress", is("Test Address 0")))
//                .andExpect(jsonPath("$[1].fullAddress", is("Test Address 1")));
//    }
//}
