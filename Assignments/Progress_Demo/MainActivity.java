package com.example.progressdemo;

import android.os.Bundle;
import android.os.Handler;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar, circularProgress;
    Button btnStart;
    TextView txtStatus, txtPercent;

    int progress = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        circularProgress = findViewById(R.id.circularProgress);
        btnStart = findViewById(R.id.btnStart);
        txtStatus = findViewById(R.id.txtStatus);
        txtPercent = findViewById(R.id.txtPercent);

        btnStart.setOnClickListener(v -> {

            progress = 0;
            txtStatus.setText("Uploading...");
            circularProgress.setVisibility(ProgressBar.VISIBLE);

            new Thread(() -> {
                while (progress <= 100) {

                    int current = progress;

                    handler.post(() -> {
                        progressBar.setProgress(current);
                        txtPercent.setText(current + "%");
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progress++;

                    if (progress == 100) {
                        handler.post(() -> {
                            txtStatus.setText("Upload Complete ");
                            circularProgress.setVisibility(ProgressBar.GONE);
                            Toast.makeText(this, "Upload Finished", Toast.LENGTH_SHORT).show();
                        });
                    }
                }
            }).start();
        });
    }
}