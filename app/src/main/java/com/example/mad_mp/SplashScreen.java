package com.example.mad_mp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 seconds
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        // Initialize ProgressBar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true); // Ensure the loading circle spins

        // Delay to transition to the main activity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, signup.class);
            startActivity(intent);
            finish(); // Prevent navigating back to the splash screen
        }, SPLASH_DELAY);




    }
}