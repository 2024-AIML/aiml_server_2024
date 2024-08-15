package com.Member.aiml_server_2024.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import com.Member.aiml_server_2024.model.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ShelterService {

    private final Firestore firestore;

    @Autowired
    public ShelterService(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<String> getShelterAddresses() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection("shelterList").get();
        QuerySnapshot querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        return documents.stream()
                .map(doc -> doc.toObject(Shelter.class).getFullAddress())
                .map(this::getFirstTwoWords)
                .collect(Collectors.toList());
    }

    private String getFirstTwoWords(String address) {
        String[] words = address.split("\\s+");
        if (words.length < 3) {
            return address; // 두 단어 이하의 경우 전체 반환
        }
        return String.join(" ", words[0], words[1]);
    }
}
