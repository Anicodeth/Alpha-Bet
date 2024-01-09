package com.example.alphabet;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alphabet.service.MatchesService;
import com.example.alphabet.view.BetActivity;
import com.example.alphabet.view.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static TextView resultTextView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();
        // Find the TextView by ID
        resultTextView = findViewById(R.id.resultTextView);

        // Check if the user is already logged in
        firebaseAuth.signOut();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            // User is not logged in, navigate to sign up
            navigateToSignUp();
        } else {
            // User is logged in, execute the AsyncTask to make the HTTP request
            navigateToBetActivity();
            new MatchesServiceTask().execute();

        }
    }

    // AsyncTask to perform the HTTP request in the background
    private static class MatchesServiceTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            MatchesService matchesService = new MatchesService();
            return matchesService.doInBackground();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Update the TextView with the result
            resultTextView.setText(result);


            // Navigate to BetActivity since the user is logged in

        }
    }

    // Method to navigate to the sign-up screen
    private void navigateToSignUp() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
        finish(); // Close the current activity to prevent going back to it
    }

    // Method to navigate to BetActivity
    private void navigateToBetActivity() {
        Intent betActivityIntent = new Intent(getApplicationContext(), BetActivity.class);
        startActivity(betActivityIntent);
        finish(); // Close the current activity to prevent going back to it
    }
}
