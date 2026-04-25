package com.example.file_handling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView outputText;
    Button saveBtn, readBtn;

    String fileName = "myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
        saveBtn = findViewById(R.id.saveBtn);
        readBtn = findViewById(R.id.readBtn);

        // SAVE FILE
        saveBtn.setOnClickListener(v -> {
            String data = inputText.getText().toString();

            try {
                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();

                Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // READ FILE
        readBtn.setOnClickListener(v -> {
            try {
                FileInputStream fis = openFileInput(fileName);

                int i;
                StringBuilder data = new StringBuilder();

                while ((i = fis.read()) != -1) {
                    data.append((char) i);
                }

                fis.close();
                outputText.setText(data.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}