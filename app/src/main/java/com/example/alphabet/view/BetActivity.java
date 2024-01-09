package com.example.alphabet.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.alphabet.R;
import com.example.alphabet.fragment.BetsFragment;
import com.example.alphabet.fragment.UserProfileFragment;

public class BetActivity extends AppCompatActivity {

    private ImageButton betButton;
    private ImageButton userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        betButton = findViewById(R.id.betButton);
        userButton = findViewById(R.id.userButton);

        // Set initial fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new BetsFragment())
                .commit();

        // Set click listeners for the buttons
        betButton.setOnClickListener(v -> {
            replaceFragment(new BetsFragment());
        });

        userButton.setOnClickListener(v -> {
            replaceFragment(new UserProfileFragment());
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
