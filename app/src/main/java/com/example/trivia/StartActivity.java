package com.example.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity; // Update to correct package

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Get the start button
        Button startButton = (Button) findViewById(R.id.startButton);

        Button exitButton = (Button) findViewById(R.id.exitButton);

        // Set up the button click listener
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MainActivity when the button is clicked
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);

                // Optional: Close the StartActivity so the user cannot return to it
                finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Exit the app when the button is clicked
            }
        });
    }
}

