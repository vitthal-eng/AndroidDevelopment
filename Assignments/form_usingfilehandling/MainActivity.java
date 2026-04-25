package com.example.form_usingfilehandling;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText name, email, phone, password;
    TextView output;
    Button saveBtn, readBtn;

    String fileName = "user_data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        output = findViewById(R.id.output);

        saveBtn = findViewById(R.id.saveBtn);
        readBtn = findViewById(R.id.readBtn);

        // SAVE DATA
        saveBtn.setOnClickListener(v -> {

            String data = "Name: " + name.getText().toString() +
                    "\nEmail: " + email.getText().toString() +
                    "\nPhone: " + phone.getText().toString() +
                    "\nPassword: " + password.getText().toString() +
                    "\n-----------------------\n";

            try {
                FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
                fos.write(data.getBytes());
                fos.close();

                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();

                name.setText("");
                email.setText("");
                phone.setText("");
                password.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // READ DATA
        readBtn.setOnClickListener(v -> {
            try {
                FileInputStream fis = openFileInput(fileName);

                int i;
                StringBuilder data = new StringBuilder();

                while ((i = fis.read()) != -1) {
                    data.append((char) i);
                }

                fis.close();
                output.setText(data.toString());

            } catch (Exception e) {
                output.setText("No Data Found");
            }
        });
    }
}