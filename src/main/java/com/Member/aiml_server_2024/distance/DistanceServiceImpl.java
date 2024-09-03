package com.Member.aiml_server_2024.distance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DistanceServiceImpl implements DistanceService {

    public static final String COL_NAME = "USERS";

//    @Override
//    public String saveLocation(Location location) throws ExecutionException, InterruptedException {
//        Firestore dbFireStore = FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COL_NAME).document(location.getLocation()).set(location);
//
//        return collectionApiFuture.get().getUpdateTime().toString();
//    }

    @Override
    public Location getLocation(String location) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection(COL_NAME).document(location);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Location loc = null;
        if (!document.exists()) {
            return null;
        }

        loc = document.toObject(Location.class);
        return loc;
    }

    @Override
    public double getDistance(String startLoc, String destLoc) throws ExecutionException, InterruptedException {

        DistanceImpl dist = new DistanceImpl();

        Location start = getLocation(startLoc);
        Location destination = getLocation(destLoc);

        double lat1 = start.getLatitude();
        double lon1 = start.getLongitude();
        double lat2 = destination.getLatitude();
        double lon2 = destination.getLongitude();

        double resutl = dist.distance(lat1, lon1, lat2, lon2);

        return resutl;

    }

}
