package com.example.a2020_1_service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class NewsService2 extends IntentService {


    public NewsService2() {
        super("NewsService2");
    }


    protected void onHandleIntent(Intent intent) {
        String[] arNews = {
                "4T SSD 10만원대 진입",
                "갤럭시S8 판매 호조",
                "핵융합 발전소 건설 완료",
        };
        for (int idx=0; idx<arNews.length; idx++) {
            Message msg = new Message();
            msg.what = 0;
            msg.obj = arNews[idx % arNews.length];
            mHandler.sendMessage(msg);
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                ;
            }

        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String news = (String)msg.obj;
                Toast.makeText(NewsService2.this, news, Toast.LENGTH_SHORT).show();
            }
        }
    };

}
