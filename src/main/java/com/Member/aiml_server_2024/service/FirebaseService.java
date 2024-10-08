//package com.Member.aiml_server_2024.service;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import org.springframework.stereotype.Service;
//
//@Service
//// 데이터 접근 서비스
//public class FirebaseService {
//    private final FirebaseDatabase firebaseDatabase;
//
//    public FirebaseService(){
//        this.firebaseDatabase = FirebaseDatabase.getInstance();
//    }
//
//    public DatabaseReference getDatabaseReference(String path){
//        return firebaseDatabase.getReference(path);
//    }
//}
package com.Member.aiml_server_2024.service;

import com.Member.aiml_server_2024.model.Shelter;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    private final Firestore firestore;

    @Autowired
    public FirebaseService(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<Shelter> getSheltersByPartialAddress(String inputAddress) throws ExecutionException, InterruptedException {
        CollectionReference shelters = firestore.collection("shelterList");

        // Fetch all documents in the collection
        ApiFuture<QuerySnapshot> query = shelters.get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        List<Shelter> shelterList = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            Shelter shelter = document.toObject(Shelter.class);

            // Use the method in Shelter class to get the first two words of the address
            String partialAddress = shelter.getFirstTwoWords();

            // If the partial address matches the input address, add to the list
            if (partialAddress.equals(inputAddress)) {
                shelterList.add(shelter);
            }
        }
        return shelterList;
    }
}
