package com.example.a2020_1_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewsController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_controller);
    }

    public void mOnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.newsstart:
                intent = new Intent(this, NewsService2.class);
                startService(intent);
                break;
            case R.id.newsend:
                intent = new Intent(this, NewsService2.class);
                stopService(intent);
                break;
        }
    }
}
