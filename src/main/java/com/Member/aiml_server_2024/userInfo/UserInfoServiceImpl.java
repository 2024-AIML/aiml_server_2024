package com.Member.aiml_server_2024.userInfo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    public static final String COLLECTION_NAME = "USERS";

    @Override
    public void saveUserInfo(UserInfo userInfo) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFUture = db.collection(COLLECTION_NAME).document(userInfo.getId()).set(userInfo);
    }   // id 반환
    
    @Override
    public UserInfo getUserInfo(String name) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection(COLLECTION_NAME).document(name);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        UserInfo userinfo = null;
        if (!document.exists()) {
            return null;
        }

        userinfo = document.toObject(UserInfo.class);
        return userinfo;
    }
    
    
}
