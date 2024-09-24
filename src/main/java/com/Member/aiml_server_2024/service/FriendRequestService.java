package com.Member.aiml_server_2024.service;

import com.Member.aiml_server_2024.model.FriendRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {
    private static final String COLLECTION_NAME = "friends"; // Firestore에서의 컬렉션 이름

    public void addFriend(FriendRequest friendRequest) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference friends = db.collection(COLLECTION_NAME);
        ApiFuture<WriteResult> future = friends.document(friendRequest.getFriendId()).set(friendRequest);
        future.get();
    }
}
