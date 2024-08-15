package com.Member.aiml_server_2024.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
// 데이터 접근 서비스
public class FirebaseService {
    private final FirebaseDatabase firebaseDatabase;

    public FirebaseService(){
        this.firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public DatabaseReference getDatabaseReference(String path){
        return firebaseDatabase.getReference(path);
    }
}
