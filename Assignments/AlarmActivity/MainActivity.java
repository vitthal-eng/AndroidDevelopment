package com.example.alarmactivity;

import android.app.*;
import android.content.*;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnDate, btnRingtone, btnSetAlarm;
    TimePicker timePicker;

    Calendar selectedDateTime;
    Uri ringtoneUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDate = findViewById(R.id.btnDate);
        btnRingtone = findViewById(R.id.btnRingtone);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(false);

        selectedDateTime = Calendar.getInstance();

        btnDate.setOnClickListener(v -> {
            Calendar today = Calendar.getInstance();

            DatePickerDialog dialog = new DatePickerDialog(
                    MainActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        selectedDateTime.set(year, month, dayOfMonth);
                        btnDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    },
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DAY_OF_MONTH)
            );

            dialog.getDatePicker().setMinDate(System.currentTimeMillis());
            dialog.show();
        });
        btnRingtone.setOnClickListener(v -> {
            Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
            startActivityForResult(intent, 1);
        });

        // SET ALARM
        btnSetAlarm.setOnClickListener(v -> {

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            selectedDateTime.set(Calendar.HOUR_OF_DAY, hour);
            selectedDateTime.set(Calendar.MINUTE, minute);
            selectedDateTime.set(Calendar.SECOND, 0);

            setAlarm(selectedDateTime.getTimeInMillis());

            Toast.makeText(this, "Alarm Set!", Toast.LENGTH_SHORT).show();
        });
    }

    private void setAlarm(long timeInMillis) {

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("ringtone", ringtoneUri != null ? ringtoneUri.toString() : "");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ringtoneUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            btnRingtone.setText("Ringtone Selected");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}