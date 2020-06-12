package com.example.a2020_1_service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class NewsService extends Service {
    boolean mQuit;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Service End", Toast.LENGTH_SHORT).show();
        mQuit = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        mQuit = false;
        NewsThread thread = new NewsThread(this, mHandler);
        thread.start();
        return START_STICKY;

    }

    public NewsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class NewsThread extends Thread {
        NewsService mParent;
        Handler mHandler;
        String[] arNews = {
                "일본, 독도는 한국땅으로 인정",
                "번데기 맛 초코파이 출시",
                "춘천 지역에 초거대 유전 발견",
                "한국 월드컵 결승 진출",
                "국민 소득 6만불 돌파",
                "학교 폭력 완전 근절된 것으로 조사",
                "안드로이드 점유율 아이폰을 앞질렀다.",
        };

        public NewsThread(NewsService parent, Handler handler) {
            mParent = parent;
            mHandler = handler;
        }
        public void run() {
            for (int idx=0; mQuit==false; idx++) {
                Message msg = new Message();
                msg.what = 0;
                msg.obj = arNews[idx % arNews.length];
                mHandler.sendMessage(msg);
                try { Thread.sleep(5000); } catch (Exception e) { ; }
            }
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String news = (String)msg.obj;
                Toast.makeText(NewsService.this, news, Toast.LENGTH_SHORT).show();
            }
        }
    };
}

