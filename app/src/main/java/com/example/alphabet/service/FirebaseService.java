package com.example.alphabet.service;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.alphabet.view.LoginActivity;
import com.google.firebase.auth.AuthResult;
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

    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // User registration successful
                        String userId = mAuth.getCurrentUser().getUid();
                        // You can perform additional actions, like saving user data to the database
                         mDatabase.child("users").child(userId).setValue(email);
                    } else {
                        // User registration failed
                        Exception exception = task.getException();
                        // Handle the exception
                    }
                });
    }

    public void login(String email, String password) {
        Log.d("Here", "Here" + email + password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // You can get user information using mAuth.getCurrentUser()
                    } else {
                        // User login failed
                        Exception exception = task.getException();

                    }
                });
    }

    public void bet(String userId, int amount) {
        // Hypothetical bet method, update user's betting information in the database
        // For example, you might have a "bets" node in your database where you store user bets
        mDatabase.child("bets").child(userId).setValue(amount);
    }
}
