package com.example.alert;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exitBtn = findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(v -> showExitDialog());
    }

    private void showExitDialog() {

        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")

                .setPositiveButton("Yes", (dialog, which) -> {
                    finish(); // close app
                })

                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                })

                .setCancelable(false)
                .show();
    }
}