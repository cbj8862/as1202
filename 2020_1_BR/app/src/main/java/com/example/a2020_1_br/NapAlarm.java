package com.example.a2020_1_br;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class NapAlarm extends AppCompatActivity {
    static final int NAPNOTI = 1;
    NotificationManager mNotiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_alram);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("my_alarm_channel", "알람테스트", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("알람테스트");
            mNotiManager.createNotificationChannel(notificationChannel);
        }
    }
    public void mOnClick(View v) {
        Toast.makeText(NapAlarm.this, "안녕히 주무세요", Toast.LENGTH_SHORT).show();
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(NapAlarm.this, NapEnd.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent content = PendingIntent.getActivity(NapAlarm.this, 0, intent, 0);

                RemoteViews napView = new RemoteViews(getPackageName(), R.layout.activity_cusstomnotiview);

                Notification noti = new NotificationCompat.Builder(NapAlarm.this, "my_alarm_channel")
                        .setTicker("일어나세요")
                        .setSmallIcon(R.drawable.napalarm)
                        .setContentIntent(content)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        .setVibrate(new long[] {1000, 1000, 500, 500, 200, 200, 200, 200, 200, 200})
                        .setLights(0xff00ff00, 500, 500)
                        .setContent(napView)
                        .build();

                noti.flags |= (Notification.FLAG_INSISTENT | Notification.FLAG_SHOW_LIGHTS);

                mNotiManager.notify(NapAlarm.NAPNOTI, noti);
            }
        }, 5 * 1000);
    }
}
