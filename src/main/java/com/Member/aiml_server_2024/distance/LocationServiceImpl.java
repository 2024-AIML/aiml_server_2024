package com.Member.aiml_server_2024.distance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationDao locationDao;  // DB에서 location을 받는 함수
    private Location location;  // DB에서 위도, 경도, 위치를 받아옴

    @Override
    public List<Location> getLocations() throws ExecutionException, InterruptedException {
        return locationDao.getLocations();
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
