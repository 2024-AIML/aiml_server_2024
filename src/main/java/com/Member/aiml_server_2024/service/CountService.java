//package com.Member.aiml_server_2024.service;
//
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.cloud.FirestoreClient;
//import com.google.cloud.firestore.QuerySnapshot;
//import com.google.cloud.firestore.QueryDocumentSnapshot;
//import com.Member.aiml_server_2024.model.Shelter;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class CountService {
//
//    // Firestore에서 모든 대피소 데이터를 가져오는 메서드
//    public List<Shelter> getAllShelters() throws Exception {
//        Firestore firestore = FirestoreClient.getFirestore();
//        QuerySnapshot snapshot = firestore.collection("shelterList").get().get();
//        List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
//        return documents.stream()
//                .map(doc -> doc.toObject(Shelter.class))
//                .toList();
//    }
//
//    // 대피소의 nowCount 필드를 업데이트하는 메서드
//    public void updateShelterCount(String shelterId, int newCount) throws Exception {
//        Firestore firestore = FirestoreClient.getFirestore();
//        firestore.collection("shelterList").document(shelterId)
//                .update("nowCount", newCount);
//    }
//}
