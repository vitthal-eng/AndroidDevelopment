package com.example.alarmactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class AlarmReceiver extends BroadcastReceiver {

    private static Ringtone ringtone; // keep reference to stop later if needed

    @Override
    public void onReceive(Context context, Intent intent) {

        String uriString = intent.getStringExtra("ringtone");

        Uri ringtoneUri;

        if (uriString != null && !uriString.isEmpty()) {
            ringtoneUri = Uri.parse(uriString);
        } else {
            ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        }

        ringtone = RingtoneManager.getRingtone(context, ringtoneUri);

        if (ringtone != null && !ringtone.isPlaying()) {
            ringtone.play();
        }
    }

    // Optional: call this from somewhere to stop alarm
    public static void stopRingtone() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
    }
}