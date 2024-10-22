//package com.Member.aiml_server_2024.service;
//
//import com.Member.aiml_server_2024.distance.Distance;
//import com.Member.aiml_server_2024.model.Shelter;
//import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//public class ShelterService2Test {
//
//    @Mock
//    private Firestore firestore;
//
//    @Mock
//    private Distance distance;
//
//    @InjectMocks
//    private ShelterService shelterService;
//
//    @Mock
//    private ApiFuture<QuerySnapshot> queryFuture;
//
//    @Mock
//    private QuerySnapshot querySnapshot;
//
//    @Mock
//    private QueryDocumentSnapshot queryDocumentSnapshot;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetShelterAddresses() throws ExecutionException, InterruptedException {
//        Shelter shelter1 = new Shelter();
//        shelter1.setFullAddress("123 Main Street");
//
//        Shelter shelter2 = new Shelter();
//        shelter2.setFullAddress("456 Maple Avenue");
//
//        when(firestore.collection("shelterList")).thenReturn(mock(CollectionReference.class));
//        when(firestore.collection("shelterList").get()).thenReturn(queryFuture);
//        when(queryFuture.get()).thenReturn(querySnapshot);
//        when(querySnapshot.getDocuments()).thenReturn(Arrays.asList(queryDocumentSnapshot, queryDocumentSnapshot));
//        when(queryDocumentSnapshot.toObject(Shelter.class)).thenReturn(shelter1, shelter2);
//
//        // Test
//        List<String> shelterAddresses = shelterService.getShelterAddresses();
//
//        // Print results
//        System.out.println("Shelter Addresses:");
//        for (String address : shelterAddresses) {
//            System.out.println(address);
//        }
//
//        // Verify
//        assertEquals(2, shelterAddresses.size());
//        assertEquals("123 Main", shelterAddresses.get(0));
//        assertEquals("456 Maple", shelterAddresses.get(1));
//    }
//
//    @Test
//    public void testGetSheltersByPartialAddress() throws ExecutionException, InterruptedException {
//        // Mock data
//        Shelter shelter1 = new Shelter();
//        shelter1.setFullAddress("123 Main Street");
//        shelter1.setLatitude_EPSG4326(37.7749);
//        shelter1.setLongitude_EPSG4326(-122.4194);
//
//        Shelter shelter2 = new Shelter();
//        shelter2.setFullAddress("123 Main Road");
//        shelter2.setLatitude_EPSG4326(34.0522);
//        shelter2.setLongitude_EPSG4326(-118.2437);
//
//        CollectionReference collectionReference = mock(CollectionReference.class);
//        when(firestore.collection("shelterList")).thenReturn(collectionReference);
//        when(collectionReference.get()).thenReturn(queryFuture);
//        when(queryFuture.get()).thenReturn(querySnapshot);
//        when(querySnapshot.getDocuments()).thenReturn(Arrays.asList(queryDocumentSnapshot, queryDocumentSnapshot));
//        when(queryDocumentSnapshot.toObject(Shelter.class)).thenReturn(shelter1, shelter2);
//
//        // Mock distance calculations
//        when(distance.distance(any(Double.class), any(Double.class), eq(37.7749), eq(-122.4194)))
//                .thenReturn(10.0);
//        when(distance.distance(any(Double.class), any(Double.class), eq(34.0522), eq(-118.2437)))
//                .thenReturn(20.0);
//
//        // Test
//        List<Shelter> shelters = shelterService.getSheltersByPartialAddress("123 Main", 36.0, -120.0);
//
//        // Print results
//        System.out.println("Shelters by Partial Address:");
//        for (Shelter shelter : shelters) {
//            System.out.println("Address: " + shelter.getFullAddress() +
//                    ", Latitude: " + shelter.getLatitude_EPSG4326() +
//                    ", Longitude: " + shelter.getLongitude_EPSG4326());
//        }
//
//        // Verify
//        assertEquals(2, shelters.size());
//        assertEquals("123 Main Street", shelters.get(0).getFullAddress());
//        assertEquals("123 Main Road", shelters.get(1).getFullAddress());
//        verify(distance, times(2)).distance(any(Double.class), any(Double.class), any(Double.class), any(Double.class));
//    }
//}
