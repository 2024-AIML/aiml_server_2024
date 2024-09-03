package com.Member.aiml_server_2024.service;

import com.Member.aiml_server_2024.distance.Distance;
import com.Member.aiml_server_2024.model.Shelter;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ShelterService {

    private final Firestore firestore;
    private final Distance distance;

    @Autowired
    public ShelterService(Firestore firestore, Distance distance) {
        this.firestore = firestore;
        this.distance = distance;
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

    public List<Shelter> getSheltersByPartialAddress(String inputAddress, double userLat, double userLon) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection("shelterList").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        List<Shelter> filteredShelters = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            Shelter shelter = document.toObject(Shelter.class);
            if (shelter.getFirstTwoWords().equals(inputAddress)) {
                filteredShelters.add(shelter);
            }
        }

        return filteredShelters.stream()
                .sorted((s1, s2) -> {
                    double distanceToS1 = distance.distance(userLat, userLon, s1.getLatitude_EPSG4326(), s1.getLongitude_EPSG4326());
                    double distanceToS2 = distance.distance(userLat, userLon, s2.getLatitude_EPSG4326(), s2.getLongitude_EPSG4326());
                    return Double.compare(distanceToS1, distanceToS2);
                })
                .limit(5)
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
