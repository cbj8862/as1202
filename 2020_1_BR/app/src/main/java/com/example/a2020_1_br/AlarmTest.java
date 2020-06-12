package com.example.a2020_1_br;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import java.util.Calendar;

public class AlarmTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_test);
    }

    public void mOnClick(View v) {
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent sender;

        switch (v.getId()) {
            case R.id.onetime:
                intent = new Intent(this, AlarmReceiver.class);
                sender = PendingIntent.getBroadcast(this, 0, intent, 0);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.SECOND, 10);

                am.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);
                break;
            case R.id.repeat:
            case R.id.stop:
                intent = new Intent(this, DisplayScore.class);
                sender = PendingIntent.getBroadcast(this, 0, intent, 0);

                if (v.getId() == R.id.repeat) {
                    am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 60 * 1000, sender);
                }
                else {
                    am.cancel(sender);
                }
                break;
        }
    }
}
