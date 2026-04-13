package com.example.intent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvWelcome = findViewById(R.id.tvWelcome);

        String username = getIntent().getStringExtra("username");

        if(username != null) {
            tvWelcome.setText("Welcome, " + username);
        } else {
            tvWelcome.setText("Welcome User");
        }
    }
}