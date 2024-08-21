package com.Member.aiml_server_2024.distance;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
//@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
//    private final LocationDao locationDao;
//
//    @Override
//    public List<Location> getLocations() throws ExecutionException, InterruptedException {
//        return locationDao.getLocations();
//    }

    @Override
    public List<Location> getLocations() throws ExecutionException, InterruptedException {
        List<Location> list = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference collectionReference = firestore.collection("USERS");
        List<QueryDocumentSnapshot> documents = collectionReference.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(Location.class));
        }

        return list;
    }

//    @Override
//    public List<Location> getLocations() throws ExecutionException, InterruptedException {
//        List<Location> list = new ArrayList<>();
//        Firestore firestore = FirestoreClient.getFirestore();
//
//        CollectionReference collectionReference = firestore.collection("USERS");
//        List<QueryDocumentSnapshot> documents = collectionReference.get().get().getDocuments();
//        for (QueryDocumentSnapshot document : documents) {
//            list.add(document.toObject(Location.class));
//        }
//
//        return list;
//    }
}
