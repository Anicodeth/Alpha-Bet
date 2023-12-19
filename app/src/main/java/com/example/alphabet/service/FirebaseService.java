package com.example.alphabet.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public FirebaseService() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    // Now you can use mAuth and mDatabase to interact with Firebase Authentication and Realtime Database.
}