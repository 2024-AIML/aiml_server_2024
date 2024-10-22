//package com.Member.aiml_server_2024.service;
//
//import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.*;
//import com.Member.aiml_server_2024.model.Shelter;
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
//import static org.mockito.Mockito.*;
//
//public class ShelterServiceTest {
//
//    @InjectMocks
//    private ShelterService shelterService;
//
//    @Mock
//    private Firestore firestore;
//
//    @Mock
//    private CollectionReference collectionReference;
//
//    @Mock
//    private ApiFuture<QuerySnapshot> querySnapshotApiFuture;
//
//    @Mock
//    private QuerySnapshot querySnapshot;
//
//    @Mock
//    private QueryDocumentSnapshot queryDocumentSnapshot1;
//
//    @Mock
//    private QueryDocumentSnapshot queryDocumentSnapshot2;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetShelterAddresses() throws ExecutionException, InterruptedException {
//        // Mock 데이터 생성
//        Shelter shelter1 = new Shelter();
//        shelter1.setFullAddress("123 Main");
//
//        Shelter shelter2 = new Shelter();
//        shelter2.setFullAddress("456 Elm");
//
//        // Firestore 모킹
//        when(firestore.collection("shelterList")).thenReturn(collectionReference);
//        when(collectionReference.get()).thenReturn(querySnapshotApiFuture);
//        when(querySnapshotApiFuture.get()).thenReturn(querySnapshot);
//        when(querySnapshot.getDocuments()).thenReturn(Arrays.asList(queryDocumentSnapshot1, queryDocumentSnapshot2));
//        when(queryDocumentSnapshot1.toObject(Shelter.class)).thenReturn(shelter1);
//        when(queryDocumentSnapshot2.toObject(Shelter.class)).thenReturn(shelter2);
//
//        // 서비스 메서드 호출
//        List<String> addresses = shelterService.getShelterAddresses();
//
//        // 결과 출력
//        System.out.println("Shelter Addresses:");
//        addresses.forEach(System.out::println);
//
//        // 기대 결과 확인
//        assertEquals(2, addresses.size());
//        assertEquals("123 Main", addresses.get(0));
//        assertEquals("456 Elm", addresses.get(1));
//    }
//}
