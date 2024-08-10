// GetDataTest 패키지 - FirestoreServiceTest 파일
package service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.FirestoreService;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;

public class FirestoreServiceTest {

    @Mock
    private Firestore firestore;

    @InjectMocks
    private FirestoreService firestoreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserDocument() throws InterruptedException, ExecutionException {
        // Mock DocumentSnapshot
        DocumentSnapshot documentSnapshot = mock(DocumentSnapshot.class);
        when(documentSnapshot.exists()).thenReturn(true);
        when(documentSnapshot.getData()).thenReturn(Map.of("field", "value"));

        // Mock ApiFuture
        ApiFuture<DocumentSnapshot> apiFuture = mock(ApiFuture.class);
        when(apiFuture.get()).thenReturn(documentSnapshot);

        // Mock Firestore
        when(firestore.collection("users").document("sample").get()).thenReturn(apiFuture);

        // Execute the method to be tested
        firestoreService.getUserDocument();

        // Verify interactions
        verify(firestore.collection("users").document("sample")).get();
    }
}