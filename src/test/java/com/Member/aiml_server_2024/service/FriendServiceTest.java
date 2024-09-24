//package com.Member.aiml_server_2024.service;
//
//import com.Member.aiml_server_2024.model.Friend;
//import com.google.cloud.firestore.CollectionReference;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.WriteResult;
//import com.google.firebase.cloud.FirestoreClient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.concurrent.ExecutionException;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class FriendServiceTest {
//
//    @Mock
//    private Firestore db;
//
//    @Mock
//    private CollectionReference collectionReference;
//
//    @InjectMocks
//    private FriendService friendService;
//
//    @BeforeEach
//    void setUp() {
//        CollectionReference mockCollectionReference = mock(CollectionReference.class);
//        when(db.collection(anyString())).thenReturn(mockCollectionReference);
//        when(mockCollectionReference.document(anyString())).thenReturn(mock(DocumentReference.class));
//    }
//
//    @Test
//    void testAddFriend() {
//        Friend friend = new Friend();
//        assertDoesNotThrow(() -> friendService.addFriend("userId", friend));
//        verify(collectionReference, times(1)).add(friend);
//    }
//
//    @Test
//    void testDeleteFriend() {
//        assertDoesNotThrow(() -> friendService.deleteFriend("userId", "friendId"));
//        verify(collectionReference.document("friendId"), times(1)).delete();
//    }
//}
