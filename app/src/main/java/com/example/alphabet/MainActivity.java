package com.example.alphabet;

import android.os.Bundle;
import android.os.AsyncTask;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alphabet.service.MatchesService;

public class MainActivity extends AppCompatActivity {

    private static TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView by ID
        resultTextView = findViewById(R.id.resultTextView);

        // Execute the AsyncTask to make the HTTP request
        new MatchesServiceTask().execute();
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
        }
    }
}
