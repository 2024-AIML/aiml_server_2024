package com.Member.aiml_server_2024.service;

import com.Member.aiml_server_2024.model.Friend;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class FriendService {

    private static final String COLLECTION_NAME = "users";

    private Firestore db;
    public FriendService(Firestore db){
        this.db = db;
    }

    public void addFriend(String userId, Friend friend) throws ExecutionException, InterruptedException{
        Firestore db = FirestoreClient.getFirestore();

        CollectionReference friendsCollection = db.collection(COLLECTION_NAME).document(userId).collection("friends");
        friendsCollection.add(friend);
    }

    public void deleteFriend(String userID, String friendId) throws ExecutionException, InterruptedException{
        Firestore db = FirestoreClient.getFirestore();

        db.collection(COLLECTION_NAME).document(userID).collection("friends").document(friendId).delete();
    }

}
