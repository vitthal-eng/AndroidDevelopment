package com.example.relative_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int[] buttonIds = {
            R.id.btnCenter,
            R.id.btnTop,
            R.id.btnBottom,
            R.id.btnLeft,
            R.id.btnRight,
            R.id.btnTopLeft
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            Toast.makeText(this, b.getText() + " Clicked", Toast.LENGTH_SHORT).show();
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }
}