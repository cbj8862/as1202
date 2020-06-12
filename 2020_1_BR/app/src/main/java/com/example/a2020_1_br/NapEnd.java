package com.example.a2020_1_br;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NapEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_end);

        NotificationManager NM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NM.cancel(NapAlarm.NAPNOTI);

        Button btn = (Button)findViewById(R.id.end);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
