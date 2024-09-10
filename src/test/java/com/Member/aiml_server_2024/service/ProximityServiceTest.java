//package com.Member.aiml_server_2024.service;
//
//import com.Member.aiml_server_2024.distance.DistanceService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.concurrent.ExecutionException;
//
//import static org.mockito.Mockito.*;
//
//class ProximityServiceTest {
//
//    @Mock
//    private DistanceService distanceService;
//
//    @InjectMocks
//    private ProximityService proximityService;
//
//    @BeforeEach
//    void setUp() {
//        // Mockito mock 객체 초기화
//        MockitoAnnotations.openMocks(this);
//        System.out.println("테스트 초기화 완료.");
//    }
//
//    @Test
//    void testCheckAndCount() throws ExecutionException, InterruptedException {
//        // Given
//        String userLoc = "userLocation";
//        String shelterLoc = "shelterLocation";
//
//        // distanceService의 updateShelterCount가 호출될 때 아무 작업도 하지 않도록 설정
//        doNothing().when(distanceService).updateShelterCount(userLoc, shelterLoc);
//
//        // When
//        System.out.println("테스트 시작: 사용자의 위치: " + userLoc + ", 대피소의 위치: " + shelterLoc);
//        proximityService.checkCount(userLoc, shelterLoc);
//
//        // Then
//        verify(distanceService, times(1)).updateShelterCount(userLoc, shelterLoc);
//        System.out.println("테스트 성공: updateShelterCount 메서드가 정확히 호출되었습니다.");
//    }
//}
