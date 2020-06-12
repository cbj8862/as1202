package com.example.a2020_1_network;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DownloadManagerTest extends AppCompatActivity {
    DownloadManager mDm;
    long mId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager_test);

        mDm = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnqueue:
                Uri uri = Uri.parse("http://www.soen.kr/data/child2.jpg");
                DownloadManager.Request req = new DownloadManager.Request(uri);
                req.setTitle("테스트 다운로드");
                req.setDescription("이미지 파일을 다운로드 받습니다.");
                req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                mId = mDm.enqueue(req);

                IntentFilter filter = new IntentFilter();
                filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
                registerReceiver(mDownComplete, filter);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mId != 0) {
            unregisterReceiver(mDownComplete);
            mId = 0;
        }
    }

    BroadcastReceiver mDownComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "다운로드 완료", Toast.LENGTH_LONG).show();
        }
    };
}
