package com.example.a2020_1_br;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class WatchSdcard extends AppCompatActivity {
    TextView mStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_sdcard);

        mStatus = (TextView)findViewById(R.id.status);
    }

    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_REMOVED);
        filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_EJECT);
        filter.addAction(Intent.ACTION_MEDIA_NOFS);
        filter.addDataScheme("file");
        registerReceiver(mBRSdcard, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBRSdcard);
    }

    BroadcastReceiver mBRSdcard = new BroadcastReceiver() {
        int Count = 0;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Count++;
            String str = String.format("수신 회수 : %d, 위치 : %s", Count, intent.getData().toString());
            mStatus.setText(str);

            if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
                boolean readonly = intent.getBooleanExtra("read-only", false);
                String mount = "미디어 장착 : " + (readonly ? "읽기 전용":"읽기 쓰기 가능");
                Toast.makeText(context, mount, Toast.LENGTH_SHORT).show();
            }
            if (action.equals(Intent.ACTION_MEDIA_REMOVED)) {
                Toast.makeText(context, "미디어 분리", Toast.LENGTH_SHORT).show();
            }
            if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
                Toast.makeText(context, "미디어 잘못된 위치에 장착", Toast.LENGTH_SHORT).show();
            }
            if (action.equals(Intent.ACTION_MEDIA_EJECT)) {
                Toast.makeText(context, "미디어 제거 요청", Toast.LENGTH_SHORT).show();
            }
            if (action.equals(Intent.ACTION_MEDIA_NOFS)) {
                Toast.makeText(context, "미디어 인식 안됨", Toast.LENGTH_SHORT).show();
            }
        }


    };
}
