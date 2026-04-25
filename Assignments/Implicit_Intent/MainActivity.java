package com.example.explicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText email, subject, message;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(v -> {

            String emailText = email.getText().toString();
            String subjectText = subject.getText().toString();
            String messageText = message.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822"); // only email apps

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailText});
            intent.putExtra(Intent.EXTRA_SUBJECT, subjectText);
            intent.putExtra(Intent.EXTRA_TEXT, messageText);

            startActivity(Intent.createChooser(intent, "Choose Email App"));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}