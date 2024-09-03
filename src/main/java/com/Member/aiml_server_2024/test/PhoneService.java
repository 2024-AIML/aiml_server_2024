package com.Member.aiml_server_2024.test;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PhoneService {

    // Create
    public static final String COL_NAME = "phone";

    public String savePhoneInfo(Phone phone) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COL_NAME).document(phone.getPhoneName()).set(phone);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    // Read
    public Phone getPhoneInfo(String phoneNM) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection(COL_NAME).document(phoneNM);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Phone phone = null;
        if (!document.exists()) {
            return null;
        }

        phone = document.toObject(Phone.class);
        return phone;
    }

    // Update
    public String updatePhoneInfo(Phone phone) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COL_NAME).document(phone.getPhoneName()).set(phone);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    // Delete
    public String deletePhoneInfo(String phoneName) {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFireStore.collection(COL_NAME).document(phoneName).delete();
        return "삭제완료";
    }
}
