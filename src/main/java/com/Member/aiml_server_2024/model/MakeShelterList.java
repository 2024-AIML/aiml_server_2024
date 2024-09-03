package com.Member.aiml_server_2024.model;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MakeShelterList {
    @Autowired
    public Firestore firestore;

//    private Firestore firestore;

    public List<Shelter> getSheltersByPartialAddress(String inputAddress) throws ExecutionException, InterruptedException {
        CollectionReference shelters = firestore.collection("shelterList");

        // Fetch all documents in the collection
        ApiFuture<QuerySnapshot> query = shelters.get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        List<Shelter> shelterList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            Shelter shelter = document.toObject(Shelter.class);

            // Use the new method in Shelter class
            String partialAddress = shelter.getFirstTwoWords();

            // If the partial address matches the input address, add to the list
            if (partialAddress.equals(inputAddress)) {
                shelterList.add(shelter);
            }
        }
        return shelterList;
    }
}
