package com.Member.aiml_server_2024.distance;

import com.Member.aiml_server_2024.model.Shelter;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DistanceServiceImpl implements DistanceService {

    public static final String COL_NAME = "USERS";
    // 사용할 때 "shelterList" 로 바꾸기




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

        double result = dist.distance(lat1, lon1, lat2, lon2);

        return result;

    }

    @Override
    public double getDistanceByGeoPoint(GeoPoint userLoc, GeoPoint shelterLoc) throws ExecutionException, InterruptedException {
        DistanceImpl dist = new DistanceImpl();

        double lat1 = userLoc.getLatitude();
        double lon1 = userLoc.getLongitude();
        double lat2 = shelterLoc.getLatitude();
        double lon2 = shelterLoc.getLongitude();

        // 거리 계산
        double result = dist.distance(lat1, lon1, lat2, lon2);

        return result;
    }


    @Override
    public void updateShelterCount(String userLoc, String shelterLoc) throws ExecutionException, InterruptedException {
        double distance = getDistance(userLoc, shelterLoc);

        // 20m 이내일 경우, 그 밖일 경우
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference shelterDocRef = dbFirestore.collection(COL_NAME).document(shelterLoc);

        ApiFuture<DocumentSnapshot> future = shelterDocRef.get();
        DocumentSnapshot document = future.get();

        if(!document.exists()) throw new IllegalArgumentException("Shelter not found.");

        Shelter shelter = document.toObject(Shelter.class);

        if(distance <= 20) shelter.setNowCount(shelter.getNowCount() + 1);
        else shelter.setNowCount(shelter.getNowCount() - 1);

    }

}
