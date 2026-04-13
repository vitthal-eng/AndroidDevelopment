package com.example.form_using_allbuttons;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    RadioGroup radioGroup;
    RadioButton selectedRadio;
    CheckBox cbAgree;
    ToggleButton toggleBtn;
    Switch switchBtn;

    Button btnSubmit;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        radioGroup = findViewById(R.id.radioGroup);
        cbAgree = findViewById(R.id.cbAgree);
        toggleBtn = findViewById(R.id.toggleBtn);
        switchBtn = findViewById(R.id.switchBtn);

        btnSubmit = findViewById(R.id.btnSubmit);
        fab = findViewById(R.id.fab);

        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Enter your name");
                return;
            }

            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
                return;
            }

            selectedRadio = findViewById(selectedId);
            String gender = selectedRadio.getText().toString();

            if (!cbAgree.isChecked()) {
                Toast.makeText(this, "Please accept terms", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean toggleState = toggleBtn.isChecked();
            boolean switchState = switchBtn.isChecked();

            String result = "Name: " + name +
                    "\nGender: " + gender +
                    "\nTerms Accepted: Yes" +
                    "\nToggle State: " + (toggleState ? "ON" : "OFF") +
                    "\nNotifications: " + (switchState ? "Enabled" : "Disabled");

            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        });



        fab.setOnClickListener(v -> {
            Toast.makeText(this, "FAB Clicked ➕", Toast.LENGTH_SHORT).show();
        });
    }
}