package com.Member.aiml_server_2024.service;

import com.Member.aiml_server_2024.distance.DistanceServiceImpl;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CountingService {

    @Autowired
    private Firestore firestore;

    @Autowired
    private DistanceServiceImpl distanceService;

    public void updateOccupiedUsersInShelters() throws ExecutionException, InterruptedException {
        // shelterList 컬렉션의 모든 문서 가져오기
        ApiFuture<QuerySnapshot> sheltersFuture = firestore.collection("shelterList").get();
        List<QueryDocumentSnapshot> shelterDocuments = sheltersFuture.get().getDocuments();

        // users 컬렉션의 모든 문서 가져오기
        ApiFuture<QuerySnapshot> usersFuture = firestore.collection("users").get();
        List<QueryDocumentSnapshot> userDocuments = usersFuture.get().getDocuments();

        // 각 user에 대해 shelter와 거리 비교
        for (QueryDocumentSnapshot userDoc : userDocuments) {
            GeoPoint userLocation = userDoc.getGeoPoint("location"); // GeoPoint로 위도, 경도 가져오기
            String userId = userDoc.getId();

            for (QueryDocumentSnapshot shelterDoc : shelterDocuments) {
                GeoPoint shelterLocation = shelterDoc.getGeoPoint("location");
                String shelterName = shelterDoc.getString("name");

                // GeoPoint를 이용한 거리 계산 함수 호출
                double distance = distanceService.getDistanceByGeoPoint(userLocation, shelterLocation);

                if (distance <= 20.0) {
                    // 거리가 20m 이내일 때
                    // shelter의 occupied 서브 컬렉션에 사용자 추가
                    firestore.collection("shelterList")
                            .document(shelterDoc.getId())
                            .collection("occupied")
                            .document(userId)
                            .set(new HashMap<>());

                    // 사용자 문서의 here 필드 업데이트
                    firestore.collection("users")
                            .document(userId)
                            .update("here", shelterName);

                } else {
                    // 거리가 20m를 초과할 때
                    // occupied 서브 컬렉션에서 사용자 문서 삭제
                    ApiFuture<DocumentSnapshot> occupiedDoc = firestore.collection("shelterList")
                            .document(shelterDoc.getId())
                            .collection("occupied")
                            .document(userId)
                            .get();

                    if (occupiedDoc.get().exists()) {
                        // 해당 사용자가 occupied에 이미 있으면 삭제
                        firestore.collection("shelterList")
                                .document(shelterDoc.getId())
                                .collection("occupied")
                                .document(userId)
                                .delete();

                        // 사용자 문서의 here 필드를 빈 값으로 업데이트
                        firestore.collection("users")
                                .document(userId)
                                .update("here", "");
                    }
                }
            }
        }
    }
}
