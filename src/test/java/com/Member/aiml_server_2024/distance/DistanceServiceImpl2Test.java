package com.Member.aiml_server_2024.distance;

import com.Member.aiml_server_2024.model.Shelter;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DistanceServiceImpl2Test {


    @Mock
    private Firestore firestore;

    @Mock
    private CollectionReference collectionReference;

    @Mock
    private DocumentReference documentReference;

    @Mock
    private DocumentSnapshot documentSnapshot;

    @InjectMocks
    private DistanceServiceImpl distanceService;

    @BeforeAll
    static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/serviceAccountKey.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Firebase 초기화에 실패했습니다", e);
        }
    }

    @BeforeEach
    void setUp() throws ExecutionException, InterruptedException {
        MockitoAnnotations.openMocks(this);

        // Firestore의 collection 메서드가 mock CollectionReference를 반환하도록 설정
        when(firestore.collection(anyString())).thenReturn(collectionReference);

        // CollectionReference의 document 메서드가 mock DocumentReference를 반환하도록 설정
        when(collectionReference.document(anyString())).thenReturn(documentReference);

        // DocumentReference의 get 메서드가 mock ApiFuture를 반환하도록 설정
        ApiFuture<DocumentSnapshot> apiFuture = mock(ApiFuture.class);
        when(documentReference.get()).thenReturn(apiFuture);
        when(apiFuture.get()).thenReturn(documentSnapshot);
    }

    @Test
    void testGetDistance() throws ExecutionException, InterruptedException {
        // Given
        String startLoc = "startLocation";
        String destLoc = "destinationLocation";

        Location startLocation = new Location(37.5665, 126.9780, "random1"); // Example: Seoul
        Location destinationLocation = new Location(35.1796, 129.0756, "random2"); // Example: Busan

        when(documentSnapshot.exists()).thenReturn(true);
        when(documentSnapshot.toObject(Location.class)).thenReturn(startLocation);

        // Mock Firestore to return different locations based on the input
        if (startLoc.equals("startLocation")) {
            when(documentSnapshot.toObject(Location.class)).thenReturn(startLocation);
        } else if (destLoc.equals("destinationLocation")) {
            when(documentSnapshot.toObject(Location.class)).thenReturn(destinationLocation);
        }

        // When
        double distance = distanceService.getDistance(startLoc, destLoc);

        // Then
        assertTrue(distance > 0);
    }

    @Test
    void testUpdateShelterCountIfNeeded_Within20m() throws ExecutionException, InterruptedException {
        // Given
        String userLoc = "userLocation";
        String shelterLoc = "shelterLocation";

        Location userLocation = new Location(37.5665, 126.9780, "random1"); // Example: Seoul
        Location shelterLocation = new Location(37.5666, 126.9781, "random2"); // Example: Near Seoul (within 20m)

        Shelter shelter = new Shelter();
        shelter.setNowCount(10);

        when(documentSnapshot.exists()).thenReturn(true);
        when(documentSnapshot.toObject(Shelter.class)).thenReturn(shelter);

        // When
        distanceService.updateShelterCount(userLoc, shelterLoc);

        // Then
        assertEquals(11, shelter.getNowCount()); // 20m 이내에서 nowCount가 1 증가해야 함
        verify(documentReference, times(1)).set(shelter);
    }

    @Test
    void testUpdateShelterCountIfNeeded_Outside20m() throws ExecutionException, InterruptedException {
        // Given
        String userLoc = "userLocation";
        String shelterLoc = "shelterLocation";

        Location userLocation = new Location(37.5665, 126.9780, "random1"); // Example: Seoul
        Location shelterLocation = new Location(35.1796, 129.0756, "random2"); // Example: Busan

        Shelter shelter = new Shelter();
        shelter.setNowCount(10);

        when(documentSnapshot.exists()).thenReturn(true);
        when(documentSnapshot.toObject(Shelter.class)).thenReturn(shelter);

        // When
        distanceService.updateShelterCount(userLoc, shelterLoc);

        // Then
        assertEquals(9, shelter.getNowCount()); // 20m 이외에서 nowCount가 1 감소해야 함
        verify(documentReference, times(1)).set(shelter);
    }
}
