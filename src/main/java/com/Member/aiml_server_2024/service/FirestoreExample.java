
package com.Member.aiml_server_2024.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirestoreExample {

//    public static void main(String[] args) {
////        // Firestore 인스턴스 초기화
////        Firestore db = initializeFirestore();
////
////        if (db != null) {
////            // 'users' 컬렉션의 'sample' 문서에서 'name' 필드 읽어오기
////            readNameField(db, "sample");
////        } else {
////            System.err.println("Firestore fail to init client.");
////        }
////    }


    public static void main(String[] args) {
        // Firestore 인스턴스 초기화
        Firestore db = initializeFirestore();

        if (db != null) {
            // 'shelterList' 컬렉션의 상위 5개 문서에서 'Address1' 필드 읽어오기
            readTop5Addresses(db);
        } else {
            System.err.println("Firestore fail to init client.");
        }
    }

    private static Firestore initializeFirestore() {
        Firestore db = null;
        try {
            // 서비스 계정 키 파일 경로
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/serviceAccountKey.json");

            // Firestore 클라이언트 설정
            FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // Firestore 클라이언트 인스턴스 생성
            db = firestoreOptions.getService();

            System.out.println("Firestore init success");
        } catch (IOException e) {
            System.err.println("Firestore init fail: " + e.getMessage());
        }
        return db;
    }

//package com.Member.aiml_server_2024.service;
//
//import com.google.api.core.ApiFuture;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.FirestoreOptions;
//import com.google.cloud.firestore.DocumentSnapshot;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.concurrent.ExecutionException;
//
//public class FirestoreExample {
//
//    public static void main(String[] args) {
//        // Firestore 인스턴스 초기화
//        Firestore db = initializeFirestore();
//
//        if (db != null) {
//            // 'users' 컬렉션의 'sample' 문서에서 'name' 필드 읽어오기
//            readNameField(db, "sample");
//        } else {
//            System.err.println("Firestore 클라이언트가 초기화되지 않았습니다.");
//        }
//    }
//
//    private static Firestore initializeFirestore() {
//        Firestore db = null;
//        try {
//            // 서비스 계정 키 파일 경로
//            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/serviceAccountKey.json");
//
//            // Firestore 클라이언트 설정
//            FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            // Firestore 클라이언트 인스턴스 생성
//            db = firestoreOptions.getService();
//
//            System.out.println("Firestore 초기화 성공");
//        } catch (IOException e) {
//            System.err.println("Firestore 초기화 실패: " + e.getMessage());
//        }
//        return db;
//    }
//
//    private static void readNameField(Firestore db, String documentId) {
//        try {
//            // 'users' 컬렉션의 특정 문서 참조
//            DocumentReference docRef = db.collection("users").document(documentId);
//
//            // 문서 읽기
//            ApiFuture<DocumentSnapshot> future = docRef.get();
//
//            // 문서 스냅샷 얻기
//            DocumentSnapshot document = future.get();
//
//            if (document.exists()) {
//                // 'name' 필드 읽기
//                String name = document.getString("name");
//                System.out.println("문서 ID: " + documentId);
//                System.out.println("name 필드 값: " + name);
//            } else {
//                System.out.println("there is no document");
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            System.err.println("fail to read document : " + e.getMessage());
//        }
//    }

    private static void readTop5Addresses(Firestore db) {
        try {
            // 'shelterList' 컬렉션에서 상위 5개의 문서 가져오기
            ApiFuture<QuerySnapshot> future = db.collection("shelterList").limit(5).get();

            // 쿼리 스냅샷 얻기
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            // 각 문서의 'Address1' 필드 읽기
            for (QueryDocumentSnapshot document : documents) {
                String address = document.getString("Address1");
                System.out.println("문서 ID: " + document.getId());
                System.out.println("Address1 필드 값: " + address);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("fail to read documents : " + e.getMessage());
        }
    }
}
//                System.out.println("문서가 존재하지 않습니다.");
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            System.err.println("문서 읽기 실패: " + e.getMessage());
//        }
//    }
//}
