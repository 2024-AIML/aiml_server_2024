package com.Member.aiml_server_2024.service;

import com.Member.aiml_server_2024.distance.DistanceServiceImpl;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.*;

public class CountingServiceTest {

    @Mock
    private Firestore firestore;

    @Mock
    private DistanceServiceImpl distanceService;

    @InjectMocks
    private CountingService countingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateOccupiedUsersInShelters_UserWithinRange() throws ExecutionException, InterruptedException {
        // Mock Data
        GeoPoint userLocation = new GeoPoint(37.7749, -122.4194);
        GeoPoint shelterLocation = new GeoPoint(37.7749, -122.4195);

        String userId = "user1";
        String shelterId = "shelter1";
        String shelterName = "Test Shelter";

        QueryDocumentSnapshot mockUserDoc = mock(QueryDocumentSnapshot.class);
        when(mockUserDoc.getGeoPoint("location")).thenReturn(userLocation);
        when(mockUserDoc.getId()).thenReturn(userId);

        QueryDocumentSnapshot mockShelterDoc = mock(QueryDocumentSnapshot.class);
        when(mockShelterDoc.getGeoPoint("location")).thenReturn(shelterLocation);
        when(mockShelterDoc.getString("name")).thenReturn(shelterName);
        when(mockShelterDoc.getId()).thenReturn(shelterId);

        // Mocking Firestore Collections
        CollectionReference mockShelterCollection = mock(CollectionReference.class);
        ApiFuture<QuerySnapshot> mockShelterFuture = mock(ApiFuture.class);
        QuerySnapshot mockShelterSnapshot = mock(QuerySnapshot.class);
        when(mockShelterCollection.get()).thenReturn(mockShelterFuture);
        when(mockShelterFuture.get()).thenReturn(mockShelterSnapshot);
        when(mockShelterSnapshot.getDocuments()).thenReturn(Collections.singletonList(mockShelterDoc));
        when(firestore.collection("shelterList")).thenReturn(mockShelterCollection);

        CollectionReference mockUserCollection = mock(CollectionReference.class);
        ApiFuture<QuerySnapshot> mockUserFuture = mock(ApiFuture.class);
        QuerySnapshot mockUserSnapshot = mock(QuerySnapshot.class);
        when(mockUserCollection.get()).thenReturn(mockUserFuture);
        when(mockUserFuture.get()).thenReturn(mockUserSnapshot);
        when(mockUserSnapshot.getDocuments()).thenReturn(Collections.singletonList(mockUserDoc));
        when(firestore.collection("users")).thenReturn(mockUserCollection);

        // Mock Distance Calculation (within 20m)
        when(distanceService.getDistanceByGeoPoint(userLocation, shelterLocation)).thenReturn(10.0);

        // Mock Firestore Updates
        ApiFuture<WriteResult> mockWriteResult = mock(ApiFuture.class);

        // Separate Document References for Shelter and User
        DocumentReference mockShelterDocRef = mock(DocumentReference.class);
        DocumentReference mockUserDocRef = mock(DocumentReference.class);
        CollectionReference mockOccupiedCollection = mock(CollectionReference.class);
        DocumentReference mockOccupiedDocRef = mock(DocumentReference.class);

        // Mock Firestore Document References
        when(firestore.collection("shelterList").document(shelterId)).thenReturn(mockShelterDocRef);
        when(mockShelterDocRef.collection("occupied")).thenReturn(mockOccupiedCollection);
        when(mockOccupiedCollection.document(userId)).thenReturn(mockOccupiedDocRef);

        // Mock set() for adding the user to the occupied collection
        doAnswer(invocation -> {
            System.out.println("User added to occupied collection: " + userId);
            return null;
        }).when(mockOccupiedDocRef).set(anyMap());

        // Mock update() for the user's 'here' field
        when(firestore.collection("users").document(userId)).thenReturn(mockUserDocRef);
        doAnswer(invocation -> {
            System.out.println("User's 'here' field updated to: " + shelterName);
            return null;
        }).when(mockUserDocRef).update("here", shelterName);

        // Execute the method under test
        countingService.updateOccupiedUsersInShelters();

        // Verify user is added to the shelter's occupied collection
        verify(mockOccupiedDocRef).set(anyMap());

        // Verify user's here field is updated
        verify(mockUserDocRef).update("here", shelterName);
    }


    @Test
    public void testUpdateOccupiedUsersInShelters_UserOutOfRange() throws ExecutionException, InterruptedException {
        // Mock Data
        GeoPoint userLocation = new GeoPoint(37.7749, -122.4194);
        GeoPoint shelterLocation = new GeoPoint(37.7749, -122.4195);

        String userId = "user1";
        String shelterId = "shelter1";
        String shelterName = "Test Shelter";

        QueryDocumentSnapshot mockUserDoc = mock(QueryDocumentSnapshot.class);
        when(mockUserDoc.getGeoPoint("location")).thenReturn(userLocation);
        when(mockUserDoc.getId()).thenReturn(userId);

        QueryDocumentSnapshot mockShelterDoc = mock(QueryDocumentSnapshot.class);
        when(mockShelterDoc.getGeoPoint("location")).thenReturn(shelterLocation);
        when(mockShelterDoc.getString("name")).thenReturn(shelterName);
        when(mockShelterDoc.getId()).thenReturn(shelterId);

        // Mocking Firestore Collections
        CollectionReference mockShelterCollection = mock(CollectionReference.class);
        ApiFuture<QuerySnapshot> mockShelterFuture = mock(ApiFuture.class);
        QuerySnapshot mockShelterSnapshot = mock(QuerySnapshot.class);
        when(mockShelterCollection.get()).thenReturn(mockShelterFuture);
        when(mockShelterFuture.get()).thenReturn(mockShelterSnapshot);
        when(mockShelterSnapshot.getDocuments()).thenReturn(Collections.singletonList(mockShelterDoc));
        when(firestore.collection("shelterList")).thenReturn(mockShelterCollection);

        CollectionReference mockUserCollection = mock(CollectionReference.class);
        ApiFuture<QuerySnapshot> mockUserFuture = mock(ApiFuture.class);
        QuerySnapshot mockUserSnapshot = mock(QuerySnapshot.class);
        when(mockUserCollection.get()).thenReturn(mockUserFuture);
        when(mockUserFuture.get()).thenReturn(mockUserSnapshot);
        when(mockUserSnapshot.getDocuments()).thenReturn(Collections.singletonList(mockUserDoc));
        when(firestore.collection("users")).thenReturn(mockUserCollection);

        // Mock Distance Calculation (out of 20m range)
        when(distanceService.getDistanceByGeoPoint(userLocation, shelterLocation)).thenReturn(25.0);

        // Mock Firestore occupied document existence check
        DocumentReference mockShelterDocRef = mock(DocumentReference.class);
        CollectionReference mockOccupiedCollection = mock(CollectionReference.class);
        DocumentSnapshot mockOccupiedDocSnapshot = mock(DocumentSnapshot.class);

        when(firestore.collection("shelterList").document(shelterId)).thenReturn(mockShelterDocRef);
        when(mockShelterDocRef.collection("occupied")).thenReturn(mockOccupiedCollection);
        when(mockOccupiedCollection.document(userId)).thenReturn(mockShelterDocRef);
        when(mockShelterDocRef.get()).thenReturn(ApiFutures.immediateFuture(mockOccupiedDocSnapshot));
        when(mockOccupiedDocSnapshot.exists()).thenReturn(true);

        // Mock Firestore Deletes and Updates
        ApiFuture<WriteResult> mockWriteResult = mock(ApiFuture.class);
        when(mockShelterDocRef.delete()).thenReturn(mockWriteResult);
        when(firestore.collection("users").document(userId)).thenReturn(mockShelterDocRef);
        when(mockShelterDocRef.update("here", "")).thenReturn(mockWriteResult);

        // Capture the delete operation to see the output
        doAnswer(invocation -> {
            System.out.println("User removed from occupied collection");
            return null;
        }).when(mockShelterDocRef).delete();

        // Capture the update operation to see the output
        doAnswer(invocation -> {
            System.out.println("User's 'here' field updated to empty");
            return null;
        }).when(mockShelterDocRef).update("here", "");

        // Execute the method under test
        countingService.updateOccupiedUsersInShelters();

        // Verify occupied collection deletion
        verify(mockShelterDocRef).delete();

        // Verify user's here field update to empty
        verify(mockShelterDocRef).update("here", "");
    }

}
